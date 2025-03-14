#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import render_template, session, request, redirect, url_for

from connexion_db import get_db

client_article = Blueprint('client_article', __name__, template_folder='templates')


@client_article.route('/client/index')
@client_article.route('/client/article/show')      # remplace /client
def client_article_show():                                 # remplace client_index
    mycursor = get_db().cursor()

    id_user = session['user_id']

    sql = 'select * from types_meubles;'
    mycursor.execute(sql)
    types_articles = mycursor.fetchall()

    username = session['user_id']
    sql = '''select M.nom, M.id_meuble, P.panier_quantite, M.prix, P.id_PanierCouleur, P.id_PanierMateriaux,
             cl.libelle_couleur, m.libelle_materiaux, c.nbStock
             from panier P
             inner join meubles M on M.id_meuble = P.id_PanierMeuble
             left join colore c on c.id_ColoreMeuble = M.id_meuble
             left join couleur cl on cl.id_couleur = P.id_PanierCouleur
             left join materiaux m on m.id_materiaux  = P.id_PanierMateriaux
             where P.id_PanierUser = %s
             group by P.id_ajout
             ;'''
    mycursor.execute(sql, username)
    articles_panier = mycursor.fetchall()

    sql = '''select M.id_meuble, M.nom, M.prix, M.image, coalesce(sum(c.nbStock), 0) as stockTot,
           coalesce(count(A.id_avis),0) as nb_avis, coalesce(count(A.note),0) as nb_notes, avg(A.note) as moy_notes
           from meubles M
           left join colore c on c.id_ColoreMeuble = M.id_meuble
           left join avis A on A.id_AvisMeuble = id_meuble
         '''
    list_param = []
    condition_and = ""
    if "filter_word" in session or "filter_prix_min" in session or "filter_prix_max" in session or "filter_types" in session:
        sql = sql + " where "
    if "filter_word" in session:
        sql = sql + "nom like %s "
        recherche = "%" + session["filter_word"] + "%"
        list_param.append(recherche)
        condition_and = "and "
    if "filter_prix_min" in session or "filter_prix_max" in session:
        sql = sql + condition_and + "prix between %s and %s "
        list_param.append(session["filter_prix_min"])
        list_param.append(session["filter_prix_max"])
        condition_and = "and "
    if "filter_types" in session:
        sql = sql + condition_and + "("
        last_item = session['filter_types'][-1]
        for item in session['filter_types']:
            sql = sql + "id_MeubleType = %s "
            if item != last_item:
                sql = sql + "or "
            list_param.append(item)
        sql = sql + ")"
    sql = sql + "group by M.id_meuble;"
    tuple_sql = tuple(list_param)
    mycursor.execute(sql, tuple_sql)
    meubles = mycursor.fetchall()

    sql = '''select sum(M.prix * P.panier_quantite) as sous_total 
            from panier P
            inner join meubles M on M.id_meuble = P.id_PanierMeuble
            where P.id_PanierUser = %s;'''
    mycursor.execute(sql, id_user)
    prix_total = mycursor.fetchone()['sous_total']

# ======================================================================================================================
    sql = '''select id_couleur, libelle_Couleur, c.id_ColoreMeuble
                from couleur
                left join colore c on c.id_ColoreCouleur = id_couleur
                group by c.id_ColoreMeuble, id_couleur
                order by libelle_Couleur
                ;'''
    mycursor.execute(sql)
    couleur = mycursor.fetchall()
    print(couleur)

    sql = '''select id_materiaux, libelle_materiaux, c.id_ColoreMeuble
                from materiaux
                left join colore c on c.id_ColoreMateriaux = id_materiaux
                group by c.id_ColoreMeuble, id_materiaux
                order by libelle_materiaux
                ;'''
    mycursor.execute(sql)
    materiaux = mycursor.fetchall()
    print(materiaux)
    return render_template('client/boutique/panier_article.html', articlesPanier=articles_panier, meubles=meubles,
                           prix_total=prix_total, itemsFiltre=types_articles, couleur=couleur, materiaux=materiaux)


@client_article.route('/client/article/stock/<int:id_meuble>')
def client_article_stock(id_meuble):
    mycursor = get_db().cursor()

    sql='''select nom
        from meubles
        where id_meuble = %s
        ;'''
    mycursor.execute(sql, id_meuble)
    nom_meuble = mycursor.fetchone()

    sql = '''select c.libelle_couleur, col.nbStock, mat.libelle_materiaux
            from meubles
            left join colore col on col.id_ColoreMeuble = id_meuble
            inner join couleur c on c.id_couleur = col.id_ColoreCouleur
            inner join materiaux mat on mat.id_materiaux = col.id_ColoreMateriaux
            where id_meuble = %s
            ;'''
    mycursor.execute(sql, id_meuble)
    stock_dispo = mycursor.fetchall()
    labels = [str(row['libelle_couleur'])+' '+str(row['libelle_materiaux']) for row in stock_dispo]
    values = [str(row['nbStock']) for row in stock_dispo]
    return render_template('client/boutique/info_stock.html', nom_meuble=nom_meuble, labels=labels, values=values, stock_dispo=stock_dispo)


@client_article.route('/client/article/details/<int:id>', methods=['GET'])
def client_article_details(id):
    mycursor = get_db().cursor()

    sql = '''select id_meuble, nom, prix, image
             from meubles
             where id_meuble = %s;
          '''
    mycursor.execute(sql, id)
    article = mycursor.fetchall()

    sql = '''select libelle_avis, id_AvisUser, note, id_AvisMeuble, id_avis
             from avis
             where id_AvisMeuble = %s;
          '''
    mycursor.execute(sql, id)
    commentaires = mycursor.fetchall()

    id_user = session['user_id']
    sql = '''select count(C.id_cmd) as qte
             from ligne_de_commande L
             inner join commande C on C.id_cmd = L.id_LigneCmd
             inner join user U on U.id_User = C.id_CmdUser
             where L.id_LigneMeuble = %s and U.id_user = %s;
          '''
    mycursor.execute(sql, (id, id_user))
    commandes_articles = mycursor.fetchone()
    print(commandes_articles)
    return render_template('client/boutique/article_details.html', article=article, commentaires=commentaires,
                           commandes_articles=commandes_articles)

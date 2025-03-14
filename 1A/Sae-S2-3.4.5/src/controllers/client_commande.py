#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import request, render_template, flash, redirect, session
from datetime import datetime
from connexion_db import get_db

client_commande = Blueprint('client_commande', __name__, template_folder='templates')


@client_commande.route('/client/commande/add', methods=['POST'])
def client_commande_add():
    mycursor = get_db().cursor()

    date = datetime.now().strftime('%Y-%m-%d')
    id_etat = 1
    id_user = session['user_id']
    tuple = (date, id_etat, id_user)

    sql = "insert into commande(date_achat, id_CmdEtat, id_CmdUser) values(%s,%s,%s);"
    mycursor.execute(sql, tuple)

    sql = "select last_insert_id() as last_insert_id from commande where id_CmdUser = %s;"
    mycursor.execute(sql, id_user)
    commande_last_id = mycursor.fetchone()

    sql = "select * from panier where id_PanierUser = %s;"
    mycursor.execute(sql, id_user)
    panier = mycursor.fetchall()

    for item in panier:
        sql = "select prix from meubles where id_meuble = %s;"
        mycursor.execute(sql, item['id_PanierMeuble'])
        prix = mycursor.fetchone()
        sql = '''insert into ligne_de_commande(id_LigneMeuble, id_LigneCmd, id_LigneCouleur, id_LigneMateriaux, prix_unit, quantite)
              values(%s,%s,%s,%s,%s,%s);'''
        mycursor.execute(sql, (item['id_PanierMeuble'], commande_last_id['last_insert_id'], item['id_PanierCouleur'], item['id_PanierMateriaux'], prix['prix'],
                        item['panier_quantite']))

    sql = "delete from panier where id_PanierUser = %s;"
    mycursor.execute(sql, id_user)
    get_db().commit()
    flash(u'Commande ajoutée')
    return redirect('/client/article/show')


@client_commande.route('/client/commande/show', methods=['get', 'post'])
def client_commande_show():
    mycursor = get_db().cursor()
    id_user = session['user_id']
    id_cmd = request.form.get('idCommande')

    if id_cmd is None:
        articles_commande = None

    else:
        sql = '''select M.nom, couleur.libelle_couleur, materiaux.libelle_materiaux, L.quantite, M.prix, sum(L.quantite * M.prix) as prix_ligne
                from commande C
                inner join ligne_de_commande L on L.id_LigneCmd = C.id_cmd
                inner join meubles M on M.id_meuble = L.id_LigneMeuble
                left join couleur on couleur.id_couleur = L.id_LigneCouleur
                left join materiaux on materiaux.id_materiaux = L.id_LigneMateriaux
                where C.id_cmd = %s
                group by M.nom, couleur.libelle_couleur, materiaux.libelle_materiaux, L.quantite, M.prix;'''
        mycursor.execute(sql, id_cmd)
        articles_commande = mycursor.fetchall()

    sql = '''select C.date_achat, C.id_cmd, sum(L.quantite) as quantite, sum(L.quantite * M.prix) as prix_total, C.id_CmdEtat,
            E.libelle_etat
            from commande C
            inner join ligne_de_commande L on L.id_LigneCmd = C.id_cmd
            inner join meubles M on M.id_meuble = L.id_LigneMeuble
            inner join etat E on E.id_etat = C.id_CmdEtat
            where C.id_CmdUser like %s
            group by C.id_cmd;
          '''
    mycursor.execute(sql, id_user)
    commandes = mycursor.fetchall()
    return render_template('client/commandes/show.html', commandes=commandes, articles_commande=articles_commande)

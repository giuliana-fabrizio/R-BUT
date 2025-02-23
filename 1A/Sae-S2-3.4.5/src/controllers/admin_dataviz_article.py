#! /usr/bin/python
# -*- coding:utf-8 -*-
from flask import Blueprint
from flask import render_template

from connexion_db import get_db

admin_dataviz_article = Blueprint('admin_dataviz_article', __name__, template_folder='templates')


@admin_dataviz_article.route('/admin/type-article/bilan-stock')
def show_type_article_stock():
    mycursor = get_db().cursor()
    sql = '''select t.id_type, t.libelle_type, coalesce(sum(c.nbStock), 0) as nbMeubleDispo,
             coalesce(sum(m.prix * c.nbStock),0)
            as coutStock
            from meubles m
            right join types_meubles t on t.id_type=m.id_MeubleType
            left join colore c on c.id_ColoreMeuble = m.id_meuble
            group by t.id_type
            order by t.libelle_type
            ;'''
    mycursor.execute(sql)
    cout_stock = mycursor.fetchall()
    sql = '''select c.nbStock, coalesce(sum(m.prix * c.nbStock),0) as coutStock, count(m.id_meuble) as stockDispo
            from meubles m
            left join types_meubles t on t.id_type=m.id_MeubleType
            left outer join colore c on c.id_ColoreMeuble = m.id_meuble
            ;'''
    mycursor.execute(sql)
    cout_stock_tot = mycursor.fetchall()

    return render_template('admin/dataviz/etat_type_article_stock.html',
                           cout_stock=cout_stock, cout_stock_tot=cout_stock_tot)


@admin_dataviz_article.route('/admin/article/bilan')
def show_article_bilan():
    mycursor = get_db().cursor()
    sql = '''select nom, sum(c.nbStock) as nbStockTot
            from meubles
            inner join colore c on c.id_ColoreMeuble = id_meuble
            group by id_meuble
            order by nom
            ;'''
    mycursor.execute(sql)
    meubles = mycursor.fetchall()
    labels_un = [str(row['nom']) for row in meubles]
    values_un = [str(row['nbStockTot']) for row in meubles]

    sql='''select t.libelle_type, sum(c.nbStock) as nbStockTot
            from types_meubles t
            inner join meubles m on m.id_MeubleType = t.id_type
            inner join colore c on c.id_ColoreMeuble = id_meuble
            group by t.id_type
            order by t.libelle_type;
            '''
    mycursor.execute(sql)
    type = mycursor.fetchall()
    labels_deux = [str(row['libelle_type']) for row in type]
    values_deux = [str(row['nbStockTot']) for row in type]
    return render_template('admin/dataviz/etat_article_vente.html',
                           labels_un=labels_un, values_un=values_un, meubles=meubles, labels_deux=labels_deux,
                           values_deux=values_deux, type=type)

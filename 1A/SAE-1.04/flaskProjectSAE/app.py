
# ====================================================================================================================== initialisation

from flask import Flask, render_template, request, redirect, url_for, flash
import pymysql.cursors

app=Flask(__name__)
app.secret_key='une cle(token) : grain de sel(any random string)'

mydb=pymysql.connect(
  host="localhost",
  user="ggodailf",
  password="2711",
  database="BDD_ggodailf",
  charset='utf8mb4',
  cursorclass=pymysql.cursors.DictCursor
)
mycursor=mydb.cursor()


# ======================================================================================================================    accueil

@app.route('/')
def show_accueil():
    return render_template('accueil.html')

# ======================================================================================================================    Profile

@app.route('/Habitant/profile/<int:id>')
def show_Habitant_profil(id):
    sql='''select V.Immat, T.Horaire_depart, concat(D.Nom_ville , ' - ', A.Nom_ville) as Trajet 
    from Trajet T
    inner join Vehicule V on T.Immat=V.Immat
    inner join Ville D on T.Id_ville_A=D.Id_ville
    inner join Ville A on T.Id_ville_R=A.Id_ville
    where T.Id_Inscription=%s 
    order by T.Horaire_depart desc '''
    mycursor.execute(sql, id)
    propose=mycursor.fetchall()
    print(propose)
    sql='''select T.Horaire_depart, concat(H.Prenom, ' ', H.Nom) as Conducteur, concat(D.Nom_ville , ' - ', A.Nom_ville) as Trajet 
    from Trajet T
    inner join Reserve R on T.Id_trajet=R.Id_trajet
    inner join Habitant H on R.Id_Inscription=H.Id_Inscription 
    inner join Ville D on T.Id_ville_A=D.Id_ville
    inner join Ville A on T.Id_ville_R=A.Id_ville
    where R.Id_Inscription=%s 
    order by T.Horaire_depart desc '''
    mycursor.execute(sql, id)
    effectuer=mycursor.fetchall()
    print(effectuer)
    sql='''select count(T.Id_trajet) as Nb_trajet, count(R.Id_trajet) as Nb_Reservation
        from Habitant H
        inner join Trajet T on T.Id_Inscription=H.Id_Inscription
        inner join Reserve R on H.Id_Inscription=R.Id_Inscription
        where H.Id_Inscription=%s;'''
    mycursor.execute(sql, id)
    actions=mycursor.fetchall()
    print(actions)
    sql='''select concat(H.Prenom, ' ', H.Nom) as Nom, H.Telephone, H.Adresse, v.Nom_ville 
        from Habitant H
        inner join Ville v on H.Id_ville=v.Id_ville
        where Id_Inscription=%s ;'''
    mycursor.execute(sql, id)
    habitant=mycursor.fetchone()
    print(habitant)
    sql='''select Immat, Nom_Marque, Nom_Couleur 
        from Vehicule V
        inner join Marque M on M.Id_marque=V.Id_marque
        inner join Couleur C on C.Id_Couleur=V.Id_marque
        where Id_Inscription=%s ;'''
    mycursor.execute(sql, id)
    vehicules=mycursor.fetchall()
    return render_template('habitants/show_profile.html', habitant=habitant, actions=actions, vehicules=vehicules, propose=propose, effectuer=effectuer)

# ======================================================================================================================    Habitant

@app.route('/Habitant/show')
def show_Habitant():
    sql = '''select H.Id_Inscription, H.Nom, H.Prenom , H.Adresse, H.Telephone, H.Date_naissance, V.Nom_ville
       from Habitant H
       inner join Ville V on H.Id_ville = V.Id_ville;'''
    mycursor.execute(sql)
    Habitants = mycursor.fetchall()
    return render_template('habitants/show_habitant.html', Habitants=Habitants)

@app.route('/Habitant/filtre', methods=['POST'])
def valid_filtre_habitant():
    dateDepart=request.form.get('dateDepart')
    dateSortie=request.form.get('dateSortie')
    nomVille=request.form.get('nomVille')
    tuple_filtre=(dateDepart,dateSortie,nomVille)
    sql='''select H.Id_Inscription, H.Nom, H.Prenom , H.Adresse, H.Telephone, H.Date_naissance, V.Nom_ville 
        from Habitant H
        inner join Ville V on H.Id_ville = V.Id_ville # ================================================================ Nom_ville = Id_ville
        where (H.Date_naissance>%s) and (H.Date_naissance<%s) or V.Nom_ville=%s;'''
    mycursor.execute(sql,tuple_filtre)
    Habitants=mycursor.fetchall()
    print(Habitants)
    return render_template('habitants/show_habitant.html', Habitants=Habitants)

@app.route('/Habitant/add', methods=['GET'])
def add_Habitant():
    sql="select * from Ville order by Nom_ville;"
    mycursor.execute(sql)
    ville=mycursor.fetchall()
    return render_template('habitants/add_habitant.html', ville=ville)

@app.route('/Habitant/add', methods=['POST'])
def valid_add_Habitant():
    Nom=request.form.get('Nom')
    Prenom=request.form.get('Prenom')
    Adresse=request.form.get('Adresse')
    Date_naissance=request.form.get('Date_naissance')
    Telephone=request.form.get('Telephone')
    Id_ville=request.form.get('Id_ville')
    tuple_insert=(Nom, Prenom, Adresse, Date_naissance, Telephone, Id_ville)
    sql="insert into Habitant(Nom, Prenom, Adresse, Date_naissance, Telephone, Id_ville) values(%s, %s, %s, %s, %s, %s);"
    mycursor.execute(sql, tuple_insert)
    mydb.commit()
    print('Habitant inscrit , nom:', Nom, ' - prénom : ', Prenom, ' - adresse : ', Adresse, ' - date de naissance : ',
          Date_naissance, ' - Telephone : ', Telephone, ' - nom de la ville : ', Id_ville)
    message='Habitant inscrit , nom:' + Nom + ' - prénom : ' + Prenom + ' - adresse : ' + Adresse + ' - date de naissance : ' + Date_naissance + ' - Telephone : ' + Telephone + ' - nom de la ville : ' + Id_ville
    flash(message)
    return redirect(url_for('show_Habitant'))

@app.route('/Habitant/edit/<string:id>', methods=['GET'])
def edit_Habitant(id):
    sql="select * from Habitant where Id_Inscription=%s;"
    mycursor.execute(sql,id)
    habitant=mycursor.fetchone()
    sql="select * from Ville order by Nom_ville;"
    mycursor.execute(sql)
    ville=mycursor.fetchall()
    return render_template('habitants/edit_habitant.html', habitant=habitant, ville=ville)

@app.route('/Habitant/edit', methods=['POST'])
def valid_edit_Habitant():
    Id_Inscription=request.form['Id_Inscription']
    Nom=request.form['Nom']
    Prenom=request.form.get('Prenom')
    Adresse=request.form.get('Adresse')
    Date_naissance=request.form.get('Date_naissance')
    Telephone=request.form.get('Telephone')
    Id_ville=request.form.get('Id_ville')
    tuple_update=(Nom, Prenom, Adresse, Date_naissance, Telephone, Id_ville, Id_Inscription)
    sql="update Habitant set Nom=%s,Prenom=%s,Adresse=%s,Date_naissance=%s,Telephone=%s,Id_ville=%s where Id_Inscription=%s;"
    mycursor.execute(sql, tuple_update)
    mydb.commit()
    print(u'Habitant mis à jour , nom:', Nom, ' - prénom : ', Prenom, ' - adresse : ', Adresse, ' - date de naissance : ', Date_naissance, ' - Telephone : ', Telephone, ' - nom de la ville : ', Id_ville)
    message=u'Habitant mis à jour , nom:' + Nom + ' - prenom : ' + Prenom + ' - adresse : ' + Adresse + ' - date de naissance : ' + Date_naissance + ' - Telephone : ' + Telephone + ' - nom de la ville : ' + Id_ville
    flash(message)
    return redirect(url_for('show_Habitant'))

@app.route('/Habitant/delete', methods=['GET'])
def delete_Habitant():
    id=request.args.get('id')
    sql="delete from Habitant where Id_Inscription=(%s);"
    mycursor.execute(sql, id)
    mydb.commit()
    print("un habitant supprimé, identifiant :", id)
    flash('un habitant supprimé, identifiant : ' + id)
    return redirect(url_for('show_Habitant'))

# ====================================================================================================================== Trajet

@app.route('/Trajet/show')
def show_Trajet():
    sql='''select Trajet.Id_trajet, Trajet.Horaire_depart, Trajet.Nb_Place, A.Nom_ville as aller, R.Nom_ville as retour, V.Immat, H.Nom
       from Trajet 
       inner join Ville A on A.Id_ville=Trajet.Id_ville_A
       inner join Ville R on R.Id_ville=Trajet.Id_ville_R
       inner join Vehicule V on Trajet.Immat = V.Immat
       inner join Habitant H on H.Id_Inscription = Trajet.Id_Inscription;'''
    mycursor.execute(sql)
    Trajets=mycursor.fetchall()
    print(Trajets)
    return render_template('trajets/show_trajet.html', Trajets=Trajets)

@app.route('/Trajet/add', methods=['GET'])
def add_Trajet():
    sql="select * from Ville;"
    mycursor.execute(sql)
    Ville=mycursor.fetchall()
    sql="select * from Vehicule;"
    mycursor.execute(sql)
    Vehicule=mycursor.fetchall()
    sql="select * from Habitant;"
    mycursor.execute(sql)
    Habitant=mycursor.fetchall()
    return render_template('trajets/add_trajet.html', Ville=Ville, Vehicule=Vehicule, Habitant=Habitant)

@app.route('/Trajet/add', methods=['POST'])
def valid_add_Trajet():
    Horaire_depart=request.form.get('Horaire_depart')
    Nb_Place=request.form.get('Nb_Place')
    Id_ville_A=request.form.get('Id_ville_A')
    Id_ville_R=request.form.get('Id_ville_R')
    Immat=request.form.get('Immat')
    Id_Inscription=request.form.get('Id_Inscription')
    tuple_insert=(Horaire_depart,Nb_Place,Id_ville_A,Id_ville_R,Immat,Id_Inscription)
    sql="insert into Trajet(Horaire_depart, Nb_Place, Id_ville_A, Id_ville_R, Immat, Id_Inscription) values(%s,%s,%s,%s,%s,%s)"
    mycursor.execute(sql,tuple_insert)
    mydb.commit()
    print('Trajet ajouté , horaire de départ:', Horaire_depart, ' - nombre de place : ', Nb_Place, ' - ville de départ : ', Id_ville_A, ' - ville de départ : ', Id_ville_R, ' - immatriculation : ', Immat, ' - identifiant habitant : ', Id_Inscription)
    message='Trajet ajouté , horaire de départ:' + Horaire_depart + ' - nombre de place : ' + Nb_Place + ' - ville de départ : ' + Id_ville_A + ' - ville de départ : ' + Id_ville_R + ' - immatriculation : ' + Immat + ' - identifiant habitant : ' + Id_Inscription
    flash(message)
    return redirect(url_for('show_Trajet'))

@app.route('/Trajet/edit/<int:id>', methods=["GET"])
def edit_Trajet(id):
    sql="select * from Trajet where Id_trajet=%s;"
    mycursor.execute(sql,id)
    trajet=mycursor.fetchone()
    sql="select * from Ville;"
    mycursor.execute(sql)
    ville=mycursor.fetchall()
    sql="select * from Vehicule;"
    mycursor.execute(sql)
    vehicule=mycursor.fetchall()
    sql="select * from Habitant;"
    mycursor.execute(sql)
    habitant=mycursor.fetchall()
    return render_template('trajets/edit_trajet.html', trajet=trajet, ville=ville, vehicule=vehicule, habitant=habitant)

@app.route('/Trajet/edit', methods=['POST'])
def valid_edit_Trajet():
    Id_trajet=request.form['Id_trajet']
    Horaire_depart=request.form.get('Horaire_depart')
    Nb_Place=request.form.get('Nb_Place')
    Id_ville_A=request.form.get('Id_ville_A')
    Id_ville_R=request.form.get('Id_ville_R')
    Immat=request.form.get('Immat')
    Id_Inscription=request.form.get('Id_Inscription')
    tuple_update=(Horaire_depart, Nb_Place, Id_ville_A, Id_ville_R, Immat, Id_Inscription,Id_trajet)
    sql='''update Trajet
        set Horaire_depart=%s, Nb_Place=%s, Id_ville_A=%s, Id_ville_R=%s, Immat=%s, Id_Inscription=%s
        where Id_trajet=(%s);'''
    mycursor.execute(sql,tuple_update)
    mydb.commit()
    print('Trajet mis à jour , horaire de départ:', Horaire_depart, ' - nombre de place : ', Nb_Place, ' - ville de départ : ', Id_ville_A,
          ' - ville de départ : ', Id_ville_R, ' - immatriculation : ', Immat, ' - identifiant habitant : ', Id_Inscription)
    message='Trajet mis à jour , horaire de départ:' + Horaire_depart + ' - nombre de place : ' + Nb_Place + ' - ville de départ : ' + Id_ville_A + ' - ville de départ : ' + Id_ville_R + ' - immatriculation : ' + Immat + ' - identifiant habitant : ' + Id_Inscription
    flash(message)
    return redirect(url_for('show_Trajet'))

@app.route('/Trajet/delete', methods=['GET'])
def delete_Trajet():
    id=request.args.get('Id_trajet')
    sql="delete from Trajet where Id_trajet=(%s);"
    mycursor.execute(sql,id)
    mydb.commit()
    return redirect(url_for('show_Trajet'))

# ====================================================================================================================== Véhicule

@app.route('/Vehicule/show')
def show_Vehicule():
    sql = '''select V.Immat, H.Nom, M.Nom_Marque, C.Nom_Couleur
        from Vehicule V
        inner  join Habitant H on V.Id_Inscription = H.Id_Inscription
        inner join Marque M on V.Id_marque = M.Id_marque
        inner join Couleur C on V.Id_couleur = C.Id_couleur;'''
    mycursor.execute(sql)
    vehicule=mycursor.fetchall()
    return render_template('vehicules/show_vehicule.html', vehicule=vehicule)
@app.route('/Vehicule/add', methods=['GET'])
def add_Vehicule():
    sql = "select * from Vehicule;"
    mycursor.execute(sql)
    vehicule = mycursor.fetchall()
    sql = "select * from Marque;"
    mycursor.execute(sql)
    marque = mycursor.fetchall()
    sql = "select * from Couleur;"
    mycursor.execute(sql)
    couleur = mycursor.fetchall()
    sql = "select * from Habitant;"
    mycursor.execute(sql)
    habitant = mycursor.fetchall()
    return render_template('vehicules/add_vehicule.html', vehicule=vehicule, marque=marque, couleur=couleur, habitant=habitant)

@app.route('/Vehicule/add', methods=['POST'])
def valid_add_Vehicule():
    Immat=request.form.get('Immat')
    Id_marque=request.form.get('Id_marque')
    Id_couleur=request.form.get('Id_couleur')
    Id_Inscription=request.form.get('Id_Inscription')
    tuple_add=(Immat,Id_marque,Id_couleur,Id_Inscription)
    sql=("insert into Vehicule (Immat, Id_marque, Id_couleur, Id_Inscription) values(%s,%s,%s,%s)")
    mycursor.execute(sql,tuple_add)
    mydb.commit()
    print('Vehicule ajouté : plaque immatriculation : ', Immat, ' - marque : ', Id_marque, ' - couleur : ', Id_couleur, ' - Id_Inscription : ', Id_Inscription)
    message='Vehicule ajouté : plaque immatriculation : ' + Immat + ' - marque : ' + Id_marque + ' - couleur : ' + Id_couleur + ' - Id_Inscription : ' + Id_Inscription
    flash(message)
    return redirect(url_for('show_Vehicule'))

@app.route('/Vehicule/edit/<id>', methods=['GET'])
def edit_Vehicule(id):
    sql="select * from Vehicule where Immat=%s;"
    mycursor.execute(sql, id)
    vehicule=mycursor.fetchone()
    sql="select * from Marque;"
    mycursor.execute(sql)
    marque=mycursor.fetchall()
    sql="select * from Couleur;"
    mycursor.execute(sql)
    couleur=mycursor.fetchall()
    sql="select * from Habitant;"
    mycursor.execute(sql)
    habitant=mycursor.fetchall()
    return render_template('vehicules/edit_vehicule.html', vehicule=vehicule, marque=marque, couleur=couleur, habitant=habitant)

@app.route('/Vehicule/edit', methods=['POST'])
def valid_edit_Vehicule():
    Immat=request.form['Immat']
    Id_marque=request.form.get('Id_marque')
    Id_couleur=request.form.get('Id_couleur')
    Id_Inscription=request.form.get('Id_Inscription')
    tuple_update=(Id_marque,Id_couleur,Id_Inscription,Immat)
    sql="update Vehicule set Id_marque=%s, Id_couleur=%s, Id_Inscription=%s where Immat=%s;"
    mycursor.execute(sql, tuple_update)
    mydb.commit()
    print(tuple_update)
    print(u'Vehicule modifié : plaque immatriculation : ', Immat, ' - marque : ', Id_marque, ' - couleur : ', Id_couleur, ' - Id_Inscription : ')
    message=u'Vehicule modifié : plaque immatriculation : ' + Immat + ' - marque : ' + Id_marque + ' - couleur : ' + Id_couleur + ' - Id_inscription : ' + Id_Inscription
    flash(message)
    return redirect(url_for('show_Vehicule'))


@app.route('/Vehicule/delete', methods=['GET'])
def delete_Vehicule():
    id=request.args.get('Immat')
    sql="delete from Vehicule where Immat=(%s);"
    mycursor.execute(sql,id)
    mydb.commit()
    return redirect(url_for('show_Vehicule'))


# ========================================================================== Reserve
@app.route('/Reserve/show')
def show_Reserve():
    sql= '''select concat(H.Prenom, ' ', H.Nom) as Passager, concat(D.Nom_ville , ' - ', A.Nom_ville) as Trajet,
        T.Horaire_depart, T.Immat, concat(C.Prenom, ' ', C.Nom) as Conducteur, R.Id_Inscription, R.Id_trajet,
        R.Id_Inscription as Id_Passager, T.Id_Inscription as Id_Conducteur
        from Reserve R
        inner join Habitant H on R.Id_Inscription=H.Id_Inscription
        inner join Trajet T on R.Id_trajet=T.Id_trajet
        inner join Ville D on T.Id_ville_A=D.Id_ville
        inner join Ville A on T.Id_ville_R=A.Id_ville
        inner join Habitant C on T.Id_Inscription=C.Id_Inscription
        order by C.Id_Inscription;'''
    mycursor.execute(sql)
    Reserve=mycursor.fetchall()
    return render_template('reserve/show_reserve.html', Reserve=Reserve)

@app.route('/Reserve/add', methods=['GET'])
def add_Reservation():
    sql="select Id_trajet, Horaire_depart, Nb_Place, concat(Id_ville_A, Id_ville_R) as Trajet, Immat from Trajet;"
    mycursor.execute(sql)
    Trajets=mycursor.fetchall()
    return render_template('reserve/add_reserve.html', Trajets=Trajets)

@app.route('/Reserve/add', methods=['POST'])
def valid_add_Reservation():
    Id_inscription=request.form.get('Id_inscription')
    Id_trajet= request.form.get('Id_trajet')
    tuple_add=(Id_inscription, Id_trajet)
    print(tuple_add)
    sql="insert into Reserve (Id_Inscription, Id_trajet) values(%s,%s)"
    mycursor.execute(sql, tuple_add)
    mydb.commit()
    print('Trajet ajouté , Id_Inscription : ', Id_inscription, ', Id_trajet : ', Id_trajet)
    message='Trajet ajouté , Id_Inscription : ' + Id_inscription + ', Id_trajet : '+ Id_trajet
    flash(message)
    return redirect(url_for('show_Reserve'))

@app.route('/Reserve/delete', methods=['GET'])
def delete_Reserve():
    id=request.args['id']
    trajet=request.args['trajet']
    tuple_delete=(id, trajet)
    sql="delete from Reserve where Id_Inscription=%s and Id_trajet=%s ;"
    mycursor.execute(sql, tuple_delete)
    mydb.commit()
    return redirect(url_for('show_Reserve'))

if __name__ == '__main__':
    app.run()
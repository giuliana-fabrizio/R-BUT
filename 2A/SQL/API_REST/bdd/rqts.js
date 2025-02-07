// -- ================================================== f1
export const lrt = "select id_lrt, firstname, surname\
                    from laureates\
                    ;";


// -- ================================================== f2
export const infoLrt = "select lrt.id_lrt, lrt.firstname, lrt.surname, obt.motivation, prz.annee\
                        from laureates as lrt\
                        inner join obtient as obt on obt.id_lrt = lrt.id_lrt\
                        inner join prizes as prz on prz.id_prize = obt.id_prize\
                        where obt.id_lrt = $1\
                        ;";


// -- ================================================== f3
export const plrsPrix = "select lrt.firstname, lrt.surname, count(obt.id_lrt) as nbPrix\
                        from obtient as obt\
                        inner join laureates as lrt on lrt.id_lrt = obt.id_lrt\
                        group by lrt.id_lrt\
                        having count(obt.id_lrt) > 1\
                        ;";


// -- ================================================== f4
export const ttCat = "select libelle_category from category;";


// -- ================================================== f5

export const maxPrix = "select libelle_category, count(obt.id_lrt) as nbLrt\
                        from category as cat\
                        inner join prizes as prz on prz.id_category = cat.id_category\
                        inner join obtient as obt on obt.id_prize = prz.id_prize\
                        group by cat.libelle_category\
                        order by nbLrt desc\
                        limit 1\
                        ;";

// -- ================================================== f6
export const nbPrixParAnnee = "select prz.annee, count(obt.id_lrt) as nbLrt\
                            from obtient as obt\
                            right join prizes as prz on prz.id_prize = obt.id_prize\
                            group by prz.annee\
                            order by prz.annee desc\
                            ;";


// -- ================================================== f7
export const pasDePrix = "select distinct prz.annee\
                            from prizes as prz\
                            where prz.annee not in (\
                                select annee\
                                from obtient\
                                inner join prizes on prizes.id_prize = obtient.id_prize\
                            )\
                            order by prz.annee asc\
                            ;";


// -- ================================================== f8
export const nbPrixParAnneeTrieAsc = "select prz.annee, count(obt.id_lrt) as nbLrt\
                                    from obtient as obt\
                                    inner join prizes as prz on prz.id_prize = obt.id_prize\
                                    group by prz.annee\
                                    order by nbLrt asc, prz.annee asc\
                                    ;";

export const nbPrixParAnneeTrieDesc = "select prz.annee, count(obt.id_lrt) as nbLrt\
                                    from obtient as obt\
                                    inner join prizes as prz on prz.id_prize = obt.id_prize\
                                    group by prz.annee\
                                    order by nbLrt desc, prz.annee desc\
                                    ;";


// -- ================================================== f9
export const supLrt = "delete from laureates where id_lrt = $1;";


// -- ================================================== f10

export const verifId = "select * from laureates where id_lrt = $1;";

export const verifAnnee = "select * from prizes where annee = $1;";

export const verifCat = "select * from category where libelle_category = $1;";

export const updateMot = "update obtient obt1 set motivation = $1\
                        from obtient obt2\
                        inner join prizes as prz on prz.id_prize = obt2.id_prize\
                        inner join category as cat on cat.id_category = prz.id_category\
                        where obt1.id_lrt = $2 and prz.annee = $3 and cat.libelle_category = $4\
                                and obt1.id_lrt = obt2.id_lrt and obt1.id_prize = obt2.id_prize\
                        ;";

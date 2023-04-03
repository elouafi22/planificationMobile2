package com.example.planificationmobile2.traitementPourProjet;

public class projet {
    private String idProjet;
    private String nomProjet;
    private String descriptionProjet;
    private String dateDebut;
    private String chefProjet;
    private String etatProjet;

    public projet(String idProjet, String nomProjet, String descriptionProjet, String dateDebut, String chefProjet, String etatProjet){
        this.idProjet=idProjet;
        this.chefProjet=chefProjet;
        this.descriptionProjet=descriptionProjet;
        this.dateDebut=dateDebut;
        this.nomProjet=nomProjet;
        this.etatProjet=etatProjet;
    }

    public String getNom(){
       return this.nomProjet;
    }
    public  String getDescriptionProjet(){
        return this.descriptionProjet;
    }

    public  String getChefProjet(){
        return  this.chefProjet;
    }

    public String getDateDebut(){
        return this.dateDebut;
    }
    public String getEtatProjet(){
        return this.etatProjet;
    }

    public String toString(){
        return this.nomProjet+"::"+this.etatProjet+"::"+this.descriptionProjet+"::"+this.dateDebut+"::"+this.chefProjet;
    }

    public boolean equals(projet o){
        return this.idProjet.equals(o.idProjet);
    }

}

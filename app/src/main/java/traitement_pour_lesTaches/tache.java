package traitement_pour_lesTaches;

import java.time.LocalDateTime;

public class tache {

    private int idtache;
    private LocalDateTime date_creation;
    private LocalDateTime date_fin;
    private LocalDateTime duree_estimee;
    private String etat ;
    private String description;
    private int Score;
    private int idprojet;
    private String nomEmp;

    public  tache(int idtache,LocalDateTime date_creation,LocalDateTime date_fin,LocalDateTime duree_estimee,String etat,String description,int score,int idprojet,String nomEmp){
        this.idtache=idtache;
        this.date_creation=date_creation;
        this.date_fin=date_fin;
        this.duree_estimee=duree_estimee;
        this.etat=etat;
        this.description=description;
        this.Score=score;
        this.idprojet=idprojet;
        this.nomEmp=nomEmp;
    }



    //////// les geters

    public int getIdtache() {
        return idtache;
    }

    public LocalDateTime getDate_creation() {
        return date_creation;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public LocalDateTime getDuree_estimee() {
        return duree_estimee;
    }

    public String getEtat() {
        return etat;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return Score;
    }

    public int getIdprojet() {
        return idprojet;
    }

    public String getNomEmp() {
        return nomEmp;
    }


}

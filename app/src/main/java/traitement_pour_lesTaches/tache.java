package traitement_pour_lesTaches;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

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

    /**
     * methode permet de recuperer la duree restant sur la fin d'un projet
     * @return
     */
    public String dureRestant(){
        String dureeRest="";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime dateAujourdhuit =LocalDateTime.now();
            Period period = Period.between(dateAujourdhuit.toLocalDate(),duree_estimee.toLocalDate());

            if(period.isNegative()){
                long heurePasse= ChronoUnit.HOURS.between(duree_estimee,dateAujourdhuit);
                if(heurePasse<-24)
                    return "-"+heurePasse+"h";
                ////////// pour les joure passe
                return "-"+ChronoUnit.DAYS.between(duree_estimee,dateAujourdhuit)+"j";
            } else if (period.isZero()) {

                return "Aujourd'hui";

            } else if (period.getMonths()>0) {
                if(period.getMonths()>365)
                    return ""+duree_estimee.toLocalDate();
                return "+"+ period.getMonths()+" j";
            } else if (period.getDays()>0) {
                return "+"+period.getDays()+" h";
            }else{
                Duration duration = Duration.between(LocalDateTime.now(),duree_estimee);
                long heuresRestantes = duration.toHours();
                long minutesRestantes = duration.toMinutes() % 60;
                if(heuresRestantes<0)
                    return "+"+heuresRestantes+" h";
                return "+"+minutesRestantes+" min";
            }
        }

        return  dureeRest;
    }


}

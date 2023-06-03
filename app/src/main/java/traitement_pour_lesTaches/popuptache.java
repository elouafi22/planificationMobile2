package traitement_pour_lesTaches;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.planificationmobile2.R;


public class popuptache extends Dialog {

    private TextView nomtache;
    private TextView descptiontache;
    private TextView statueprojet;
    private TextView  emp;
    private TextView chef;
    private  TextView nomprojet;
    private TextView dateFin;
    private Button butonok;

    public popuptache(Context activity){
        super(activity, androidx.appcompat.R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.popuptache2);
        this.nomtache=findViewById(R.id.popusNomtache);
        this.descptiontache=findViewById(R.id.popusdescriptiontachereponce);
        this.dateFin=findViewById(R.id.datefintachevaleur);
        this.emp=findViewById(R.id.lespersonnetachevaleur);
        this.chef=findViewById(R.id.valeurchefprojettache);
        this.nomprojet=findViewById(R.id.valeurnomprojettache);


    }

    public void buils(String nomtache,String emp,String chef, String nomprojet, String descriptionprojet, String dateFin) {
        show();
        setNomtache(nomtache);
        setnomprojet(nomprojet);
        setDescriptiontache(descriptionprojet);
        // setStatueprojet(statueprojet);
        setDateFin(""+dateFin);
        setEmp(emp);
        setChef(chef);
    }

    public void setNomtache(String nomtache){ this.nomtache.setText(nomtache);}

    public void setnomprojet(String nomprojet){
        this.nomprojet.setText(nomprojet);
    }

    public void setDescriptiontache(String descriptiontache){
        this.descptiontache.setText(descriptiontache);
    }

    public void setStatueprojet(String statueprojet){
        this.statueprojet.setText(statueprojet);
    }
    public void setDateFin(String dateFin){
        this.dateFin.setText(dateFin);
    }

    public void  setEmp(String emp){this.emp.setText(emp);}
    public void setChef(String chef){this.chef.setText(chef);}


    public Button getButonok(){
        return  this.butonok;
    }
}

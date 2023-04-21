package traitement_pour_lesTaches;

import com.example.planificationmobile2.traitementPourProjet.projet;

public class projetRf {
    private  static projetRf projet1;

    String  idproj;

    public static projetRf getInstance(){
        if(projet1==null)
            projet1=new projetRf();
        return projet1;
    }

    public void setIdproj(String idproj){
        this.idproj=idproj;
    }

    public String  getidproj(){
        return this.idproj;
    }

}

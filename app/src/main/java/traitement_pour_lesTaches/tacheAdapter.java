package traitement_pour_lesTaches;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planificationmobile2.R;
import com.example.planificationmobile2.traitementPourProjet.popupprojet;
import com.example.planificationmobile2.traitementPourProjet.projet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class tacheAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<tache> lestache;

    private Context context;

    public tacheAdapter(Context context,ArrayList<tache> lestache){
        this.context=context;
        this.lestache=lestache;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.lestache.size();
    }

    @Override
    public tache getItem(int position) {
        return this.lestache.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= this.inflater.inflate(R.layout.adaptertache,null);
        tache tacheCourant = getItem(i);
        /**
         * recuperation des item depuit la vue
         */
        TextView descriptionTache = view.findViewById(R.id.descriptiontache);
        TextView dureeRestant =view.findViewById(R.id.durerestanttache);
        TextView statusTache = view.findViewById(R.id.statuttache);
        TextView validerTache= view.findViewById(R.id.validerTache);

        /**
         * ajouter le contunue des tache dans les items
         */

        descriptionTache.setText(tacheCourant.getDescription());
        statusTache.setText(tacheCourant.getEtat());
        dureeRestant.setText(tacheCourant.dureRestant());


        /**
         * gerer l'evenement de clik sur le boutant valider tache
         */

        validerTache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "valider projet", Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }
}

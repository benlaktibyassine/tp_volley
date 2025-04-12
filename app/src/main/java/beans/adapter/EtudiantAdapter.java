package beans.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import com.example.projetws.R;
import beans.Etudiant;

public class EtudiantAdapter extends RecyclerView.Adapter<EtudiantAdapter.EtudiantViewHolder> {

    private List<Etudiant> etudiantList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Etudiant etudiant);
    }

    public EtudiantAdapter(List<Etudiant> etudiantList, OnItemClickListener listener) {
        this.etudiantList = etudiantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EtudiantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_etudiant, parent, false);
        return new EtudiantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtudiantViewHolder holder, int position) {
        Etudiant etudiant = etudiantList.get(position);
        holder.bind(etudiant, listener);
    }

    @Override
    public int getItemCount() {
        return etudiantList.size();
    }

    public static class EtudiantViewHolder extends RecyclerView.ViewHolder {
        TextView nom, prenom, ville, sexe, dateNaissance;
        ImageView image;

        public EtudiantViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.tv_nom);
            prenom = itemView.findViewById(R.id.tv_prenom);
            ville = itemView.findViewById(R.id.tv_ville);
            sexe = itemView.findViewById(R.id.tv_sexe);
            image = itemView.findViewById(R.id.iv_etudiant);
        }

        public void bind(final Etudiant etudiant, final OnItemClickListener listener) {
            nom.setText(etudiant.getNom());
            prenom.setText(etudiant.getPrenom());
            ville.setText(etudiant.getVille());
            sexe.setText(etudiant.getSexe());
            Glide.with(itemView.getContext())
                    .load(etudiant.getPhotoUrl())
                    .placeholder(R.drawable.ic_person)
                    .into(image);

            itemView.setOnClickListener(v -> listener.onItemClick(etudiant));
        }
    }

    public void updateList(List<Etudiant> newList) {
        etudiantList = newList;
        notifyDataSetChanged();
    }


}
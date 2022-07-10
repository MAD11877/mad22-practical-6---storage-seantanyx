package sg.edu.np.mad.practical2;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Viewer> {

    ArrayList<User> usersList;
    public Adapter(ArrayList<User> usersList){
        this.usersList = usersList;
    }

    @Override
    public int getItemViewType(int position) {
        if(usersList.get(position).name.endsWith("7")){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public Viewer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1){
            view  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_large, parent, false);
        }
        else{
            view  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);
        }
        return new Viewer(view);
    }

    @Override
    public void onBindViewHolder(Viewer viewer, int position) {
        User u = usersList.get(position);
        viewer.name.setText(u.name);
        viewer.desc.setText(u.description);
        viewer.profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(viewer.profPic.getContext());
                alertDialogBuilder.setTitle("Profile");
                alertDialogBuilder.setMessage(u.name);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent listact = new Intent(viewer.profPic.getContext(), MainActivity.class);
                        listact.putExtra("urFollowed", u.followed);
                        viewer.profPic.getContext().startActivity(listact);
                    }
                });

                alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}

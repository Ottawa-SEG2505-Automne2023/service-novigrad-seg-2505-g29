package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AdminManageBranches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_branches);
    }
}

/*Lecture des données Firebase et Transformation en Liste de Comptes */
DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");

databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<Account> accountList = new ArrayList<>();

        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Account account = snapshot.getValue(Account.class);
            accountList.add(account);
        }

        // Maintenant, vous avez la liste des comptes. Vous pouvez les afficher sur votre layout.
        // Appelez une méthode pour afficher les comptes sur le layout ici.
        displayAccountsOnLayout(accountList);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        // Gestion des erreurs de lecture des données Firebase
    }
});

//Affichage des Comptes sur le Layout:
public void displayAccountsOnLayout(List<Account> accountList) {
    // utilisation de la mise en page(lay-out) pour l'affichage
    // Créez un adaptateur pour afficher les comptes
    // Assurez-vous que cet adaptateur est attaché à votre RecyclerView ou ListView
}


//3eme class Suppression d'un Compte avec Mise à Jour sur Firebase et Actualisation de l'Affichage:
// Supprimez un compte lorsque nécessaire, par exemple, lorsqu'un élément de la liste est cliqué
// Assurez-vous de supprimer également le compte de Firebase
// Après la suppression, réaffichez la liste mise à jour sur votre layout

public void deleteAccount(Account accountToDelete) {
    DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference("users")
            .child(accountToDelete.getUsername());

    accountRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                // La suppression sur Firebase est réussie, maintenant mettez à jour l'affichage
                // Appelez à nouveau la méthode pour lire les données et afficher les comptes sur le layout
            } else {
                // Gestion des erreurs lors de la suppression sur Firebase
            }
        }
    });
}

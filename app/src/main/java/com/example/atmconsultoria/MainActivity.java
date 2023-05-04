package com.example.atmconsultoria;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_principal,R.id.nav_servico,
                R.id.nav_cliente, R.id.nav_contato, R.id.nav_sobre)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

/*
* Passo a Passo da implementação
*  1 - Apaguei os ids dos componentes do construtor 'AppBarConfiguration.Builder()' em MainActivity.xml.
*
*  2 - Vamos criar icones (assent vector), alterar a configuração das Strings.xml criando os valores:
*      principal, serviço, cliente, contato e sobre
*
* 3 - Alterar as configurações do activity_main.drawer.xml com a atual valores modificados do String.xml
*
* 4 - Apagar as pastas que contêm os fragments, só deixar a pasta raiz ui e o MainActivity.
*
* 5 - Criar novas pastas de fragments, são elas: principal, serviço, cliente, contato e sobre.
*
* 6 - Criar um fragment blank para todas elas.
*     OBS. Ao criar o fragment só marcar 'create layout xml'
*
* 7 - Criar os fragments (principal, serviço, cliente, contato e sobre) com os respectivas configurações
*     no mobile_navigation.xml.
*
* 8 - Configurar todos os ids no 'AppBarConfiguration.Builder()' em MainActivity.xml.
* */
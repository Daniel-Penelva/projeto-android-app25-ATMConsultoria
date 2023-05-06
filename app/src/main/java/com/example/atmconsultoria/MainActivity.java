package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
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
                enviarEmail();
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

    public void enviarEmail(){

        Intent intent = new Intent(Intent.ACTION_SEND);

        // Define o email
        intent.putExtra(intent.EXTRA_EMAIL, new String[]{"atendimento@atmconsultoria.com.br"});

        //Define o assunto do email
        intent.putExtra(intent.EXTRA_SUBJECT, "Contato pelo App");

        //Define o texto do email
        intent.putExtra(intent.EXTRA_TEXT, "Mensagem Automática");

        //Define qualquer tipo de imagem, o asteristico define qlq formato de imagem
        // Para definir um formato especifico - ex: setType("image/png");
        intent.setType("image/*");

        startActivity(Intent.createChooser(intent, "Escolha um app de email"));

        // IMPORTANTE!!! Este link possui os tipos de setType que pode ser utilizado:
        // link: https://www.sitepoint.com/mime-types-complete-list/
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
*
* ---------------------------------------------
* Intents e filtros de intents
*
* O Intent é um objeto de mensagem que pode ser usado para solicitar uma ação de outro componente do aplicativo.
* Embora os intents facilitem a comunicação entre os componentes de diversas formas, há três casos fundamentais de uso:
*
*   -> Como iniciar uma Activity (atividade)
*      Uma Activity representa uma única tela em um aplicativo. É possível iniciar uma nova instância de uma Activity
*      passando um Intent para startActivity(). O Intent descreve a atividade a iniciar e carrega todos os dados necessários.
*      Se você quiser receber um resultado da atividade quando ela finalizar, chame startActivityForResult().
*      Sua atividade recebe o resultado como um objeto Intent separado no callback de onActivityResult() da atividade.
*
*   -> Como iniciar um serviço
*      O Service é um componente que realiza operações em segundo plano sem interface do usuário. Com o Android 5.0 (API nível 21)
*      e posteriores, é possível iniciar um serviço JobScheduler.
*      Para versões anteriores ao Android 5.0 (API nível 21), é possível iniciar um service usando os métodos da classe
*      Service. É possível iniciar um serviço para realizar uma operação que acontece uma vez (como fazer o download
*      de um arquivo) passando um Intent a startService(). O Intent descreve o serviço a iniciar e carrega todos os dados
*      necessários. Se o serviço for projetado com uma interface servidor-cliente, é possível vincular ao serviço em
*      outro componente passando um Intent a bindService(). Para mais informações, consulte o guia Serviços.
*
*   -> Como fornecer uma transmissão
*      Transmissão é uma mensagem que qualquer aplicativo pode receber. O sistema fornece diversas transmissões para
*      eventos do sistema, como quando o sistema inicializa ou o dispositivo inicia o carregamento. Você pode fornecer
*      uma transmissão a outros aplicativos passando um Intent ao sendBroadcast() ou ao sendOrderedBroadcast().
*
* Tipos de intents
* Há dois tipos de intents:
*
*  => Os intents explícitos especificam qual aplicativo atenderá ao intent, fornecendo o nome do pacote do aplicativo
*     de destino ou o nome da classe de um componente totalmente qualificado. Normalmente, usa-se um intent explícito
*     para iniciar um componente no próprio aplicativo porque se sabe o nome de classe da atividade ou do serviço que
*     se quer iniciar. Por exemplo, iniciar uma nova atividade em resposta a uma ação do usuário ou iniciar um serviço
*     para fazer o download de um arquivo em segundo plano.
*
*  => Os intents implícitos não nomeiam nenhum componente específico, mas declaram uma ação geral a realizar, o que
*     permite que um componente de outro aplicativo a processe. Por exemplo, se você quiser exibir ao usuário uma
*     localização em um mapa, pode usar um intent implícito para solicitar que outro aplicativo capaz exiba uma
*     localização especificada no mapa.
*
*
*                 intent           intent
*                 |    |           |    |
*                 |    |           |    |
*     startActivity()  |           |   onCreate()
*                 |    |           |    |
*                 |    v           |    v
*        Activity A   Android System   Activity B
*             1             2              3
*
* OBS. Ilustração acima de como um intent implícito é fornecido pelo sistema para iniciar outra atividade:
*      [1] A activity (atividade) A cria um Intent com uma descrição de ação e a passa para startActivity().
*      [2] O sistema Android busca, em todos os aplicativos, um filtro de intents que corresponda ao intent. Ao encontrar
*      uma correspondência, [3] o sistema inicia a atividade correspondente (Activity B) chamando seu método onCreate() e
*      passando-lhe o Intent.
*
* A ilustração acima mostra como um intent é usado ao iniciar uma atividade. Quando o objeto Intent nomeia um componente
* específico da atividade de forma explícita, o sistema inicia o componente de imediato.
*
* Ao criar um intent implícito, o sistema Android encontra o componente adequado para iniciar, comparando o conteúdo do
* intent aos filtros de intents declarados no arquivo de manifest de outros aplicativos no dispositivo. Se o intent
* corresponder a um filtro de intents, o sistema iniciará esse componente e entregará o objeto Intent. Se diversos filtros
* de intents corresponderem, o sistema exibirá uma caixa de diálogo para que o usuário selecione o aplicativo que deseja
* usar.
*
* O filtro de intents é uma expressão em um arquivo de manifest do aplicativo que especifica o tipo de intents que
* o componente gostaria de receber. Por exemplo, ao declarar um filtro de intents para uma atividade, outros aplicativos
* se tornam capazes de iniciar diretamente sua atividade com o determinado tipo de intent. Do mesmo modo, se você não
* declarar nenhum filtro de intents para uma atividade, ela poderá ser iniciada somente com um intent explícito.
*
* Fonte: https://developer.android.com/guide/components/intents-filters?hl=pt-
* */
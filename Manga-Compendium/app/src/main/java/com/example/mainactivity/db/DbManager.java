package com.example.mainactivity.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.mainactivity.Manga;
import com.example.mainactivity.MangaState;
import com.example.mainactivity.Message;
import com.example.mainactivity.R;
import com.example.mainactivity.Thread;
import com.example.mainactivity.User;

import java.util.ArrayList;

public  class DbManager
{
    private DBhelper dbhelper;


    private static final DbManager instance = new DbManager();

    private DbManager()
    {
    }

    public static DbManager getInstance(){
        return instance;
    }

    public void createDB(Context ctx){
        dbhelper = new DBhelper(ctx);
        if(dbIsEmpty()) {
            User admin = new User("admin", "gianmarco@gmail.com", "admin", "android.resource://com.example.mainactivity/"+ R.drawable.foto_gianmarco);
            User admin2 = new User("gianmarcopalmas", "gianmarcopalms32@gmail.com", "admin2", "");

            Manga blackClover = new Manga("Black Clover", "Yuki Tabata", 2015,"Shueisha", "Weekly Shonen Jump", "fantasy, azione", 35,350, "In un mondo dove la magia è al centro della vita quotidiana sono nati Asta, un ragazzo completamente privo di ogni abilità magica, e il suo amico Yuno, che al contrario è dotato di grande forza magica legata al vento. I due sono fin da piccoli rivali e hanno deciso di competere per il titolo di Imperatore Magico, la più alta carica magica del loro regno. ", "android.resource://com.example.mainactivity/"+ R.drawable.black_clover);
            Manga bleach = new Manga("Bleach", "Tite Kubo", 2001, "Shueisha", "Weekly Shonen Jump", "avventura, azione, soprannaturale", 74, 686, "Il giovane Ichigo Kurosaki, riceve accidentalmente poteri da shinigami da Rukia Kuchiki. Oltre ad assumersi l'incarico di difendere gli esseri umani dagli spiriti maligni e di guidare le anime defunte verso l'aldilà, Ichigo viene coinvolto in una serie di scontri tra spiriti, che lo portano a esplorare vari regni dell'aldilà e a scoprire di più sulle proprie origini.", "android.resource://com.example.mainactivity/"+ R.drawable.bleach);
            Manga chainsawMan = new Manga("Chainsaw Man", "Tatsuki Fujimoto", 2018, "Shueisha", "Weekly Shonen Jump", "orrore, dark fantasy, azione", 14, 136, "Denji, un orfano senzatetto, incontra Pochita, il Diavolo Motosega, ferito a cui dona il suo sangue, stringendo inizialmente una semplice amicizia. Insieme si guadagnano da vivere cacciando Diavoli sottobanco per la yakuza per ripagare i debiti di Denji; questo finché un giorno, in uno scontro con il Diavolo Zombi (che aveva preso il controllo dei suoi creditori), Denji viene apparentemente ucciso. Pur di farlo sopravvivere, Pochita prende il posto del suo cuore, stringendo con lui un vero e proprio patto e trasformandolo così in Chainsaw Man. Divenuto un ibrido umano-diavolo, Denji elimina il Diavolo Zombi ed incontra Makima, un'ufficiale del reparto Cacciatori di Diavoli della Pubblica Sicurezza, la quale, viste le sue capacità di combattimento e affezionatasi rapidamente a lui, decide di arruolarlo nella Quarta Divisione della Pubblica Sicurezza, una sezione sperimentale nella quale viene messo alla prova l'impiego di diavoli non ostili agli esseri umani nella lotta contro altri diavoli.", "android.resource://com.example.mainactivity/"+ R.drawable.chainsaw_man);
            Manga demonSlayer = new Manga("Demon Slayer", "Koyhharu Gotogue", 2016, "Shueisha", "Weekly Shonen Jump", "avventura, dark fantasy, arti marziali", 23, 205, "Giappone, Periodo Taishō. Tanjiro è il primogenito di una numerosa famiglia orfana del padre, che vive in un'isolata casa di montagna tra i boschi. Un giorno, tornando a casa dopo essere stato al villaggio a vendere il carbone, trova la madre e i fratelli massacrati, ad eccezione della sorella Nezuko che è stata trasformata in un demone, ma ha ancora qualche pensiero ed emozione umana. Tanjiro inizia così il suo viaggio in cerca di una cura per far tornare sua sorella di nuovo umana e per impedire che la stessa tragedia accaduta a loro possa accadere ad altre persone.", "android.resource://com.example.mainactivity/"+ R.drawable.demon_slayer);
            Manga dragonBall = new Manga("Dragon Ball", "Akira Toriyama", 1984, "Shueisha", "Weekly Shonen Jump", "avventura, fantasy, azione", 42, 519, "Son Goku, un bambino con la coda di scimmia e la forza smisurata, incontra un giorno una ragazza di nome Bulma. Ella è alla ricerca delle sette sfere del drago, potenti oggetti magici che, se riuniti, permettono di evocare il drago Shenron, creatura che esaudisce un qualunque desiderio a colui che l'ha richiamato. Goku viene quindi persuaso da Bulma ad aiutarla nella ricerca delle sfere e i due partono per un lungo viaggio, nel corso del quale fanno numerosi incontri.", "android.resource://com.example.mainactivity/"+ R.drawable.dbz);
            Manga hunterHunter = new Manga("Hunter x Hunter", "Yoshihiro Togashi", 1998, "Shueisha", "Weekly Shonen Jump", "avventura, fantastico", 37, 400, "Gon Freecss è un bambino di dodici anni che vive sull'Isola Balena con la zia Mito, sua madre adottiva. Incontrando Kaito, un hunter professionista, Gon scopre che suo padre Ging, che lui non ha mai conosciuto, è vivo ed è uno dei migliori hunter al mondo e decide perciò di partecipare all'esame per diventare egli stesso un hunter allo scopo di trovarlo.", "android.resource://com.example.mainactivity/"+ R.drawable.hunter_hunter);
            Manga naruto = new Manga("Naruto", "Masashi Kishimoto", 1999, "Shueisha", "Weekly Shonen Jump", "avventura, fantasy, azione", 72, 700, "Naruto Uzumaki è un ninja dodicenne del Villaggio della Foglia con il sogno di diventare hokage, il ninja più importante del villaggio, allo scopo di essere accettato dagli altri. Naruto infatti ha passato l'infanzia nell'emarginazione e, durante lo scontro col ninja traditore Mizuki, ne scopre il motivo: dentro di lui è sigillata la Volpe a Nove Code, uno dei nove cercoteri, giganteschi demoni sovrannaturali.", "android.resource://com.example.mainactivity/"+ R.drawable.naruto);
            Manga onePiece = new Manga("One Piece", "Eiichiro Oda", 1997, "Shueisha", "Weekly Shonen Jump", "avventura, fantasy, azione", 105, 1088, "Monkey D. Rufy è un giovane pirata sognatore che da piccolo ha involontariamente mangiato un frutto del diavolo, diventando così un uomo di gomma con la capacità di allungarsi e deformarsi a piacimento. Con l'obiettivo di diventare il Re dei pirati e di ritrovare il leggendario tesoro One Piece , nascosto secondo le leggende da Gol D. Roger sull'isola di Raftel alla fine della Rotta Maggiore, Rufy si mette in mare e riunisce intorno a sé una ciurma per poter raggiungere il suo sogno.", "android.resource://com.example.mainactivity/"+ R.drawable.one_piece);
            Manga rurouniKenshin = new Manga("Rurouni Kesnhin", "Nobuhiro Watsuki", 1994, "Shueisha", "Weekly Shonen Jump", "storico, drammatico, sentimentale", 28, 255, "La narrazione ruota attorno al protagonista Battōsai Himura (parzialmente basato sul personaggio storico dell'hitokiri Kawakami Gensai), spietato assassino, che a metà 1800 contribuì alla fine dello Shogunato Tokugawa, al potere da oltre 200 anni, e all'avvento del periodo Meiji. In seguito all'insediamento del nuovo governo, Battōsai scompare per tornare sotto nuovo nome: Kenshin. Ora viaggia con una sakabato con un solo filo (una spada a lama invertita), che anziché essere rivolta verso l'avversario risiede sul lato opposto; il suo obiettivo sta nel proteggere le persone che incontra dalle ingiustizie.", "android.resource://com.example.mainactivity/"+ R.drawable.kenshin);
            Manga jujutsuKaisen = new Manga("Jujutsu Kaisen", "Gege Akutami", 2018, "Shueisha", "Weekly Shonen Jump", "avventura, dark fantasy, soprannaturale", 23, 229, "Itadori Yūji è uno studente all'apparenza normale, che di normale non ha nulla, che frequenta il Club dell'Occulto. I suoi compagni si troveranno nei guai dopo aver trovato il dito mummificato di Ryomen Sukuna, la più feroce divinità demoniaca, e per salvarli così da diventare più forte sarà costretto a ingoiarlo. Yūji diventa tutt'uno con il demone e sotto la guida del più potente degli stregoni, Satoru Gojō, entra nell'Istituto di Arti Occulte di Tokyo, un'organizzazione che combatte le Maledizioni; il suo destino è già scritto: dovrà cercare tutte le 20 dita di Sukuna, ingerirle per poi essere ucciso, così da porre fine una volta per tutte alla minaccia che alberga dentro di lui.", "android.resource://com.example.mainactivity/"+ R.drawable.jujutsu_kaisen);


            saveUser(admin.getUsername(), admin.getEmail(), admin.getPassword(), admin.getImg());
            saveUser(admin2.getUsername(), admin2.getEmail(), admin2.getPassword(), admin2.getImg());

            saveManga(blackClover);
            saveManga(bleach);
            saveManga(chainsawMan);
            saveManga(demonSlayer);
            saveManga(dragonBall);
            saveManga(hunterHunter);
            saveManga(naruto);
            saveManga(onePiece);
            saveManga(rurouniKenshin);
            saveManga(jujutsuKaisen);
        }
    }

    public boolean dbIsEmpty(){
        Cursor crs = null;
        try {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs = db.query(DbStrings.User.TABLE, null, null , null, null, null, null);

            if(crs.getCount() == 0)
                return true;

            return false;
        }
        catch(SQLiteException sqle) {System.err.println("sql errore++++++++++++++++++");}
        catch(Exception e){ System.err.println("sql errore2++++++++++++++++++++++"+e);}

        return true;
    }

    public void saveUser(String username, String email, String password, String img)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.User.USERNAME, username);
        cv.put(DbStrings.User.EMAIL, email);
        cv.put(DbStrings.User.PASSWORD, password);
        cv.put(DbStrings.User.IMAGE, img);
        try
        {
            db.insert(DbStrings.User.TABLE, null,cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }
    }


    public void createUserThread(Integer id_user, Integer id_thread)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.UserThread.ID_USER, id_user);
        cv.put(DbStrings.UserThread.ID_THREAD, id_thread);
        try
        {
            Long l= db.insert(DbStrings.UserThread.TABLE, null, cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }

    }

    public Integer createThread(Thread thread)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.Thread.IMAGE, thread.getImage());
        cv.put(DbStrings.Thread.TITLE, thread.getTitle());
        cv.put(DbStrings.Thread.DESCRIPTION, thread.getDescription());
        cv.put(DbStrings.Thread.ID_USER_CREATOR, thread.getId_creator());
        try
        {
            Long l= db.insert(DbStrings.Thread.TABLE, null, cv);
            Integer i =  l.intValue();
            return  i;
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }

        return -1;
    }


    public Thread getThreadById(Integer id){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.Thread.TABLE, null, DbStrings.Thread.ID + "=?" , new String[] {id.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToThread(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;

    }

    public void createMessage(Message message)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.Message.TEXT, message.getText());
        cv.put(DbStrings.Message.ID_USER, message.getId_user());
        cv.put(DbStrings.Message.DATE, message.getDate());
        cv.put(DbStrings.Message.ID_THREAD, message.getId_thread());
        try
        {
            db.insert(DbStrings.Message.TABLE, null,cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }
    }

    public void saveUserThread(Integer id_user, Integer id_thread)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.UserThread.ID_USER, id_user);
        cv.put(DbStrings.UserThread.ID_THREAD, id_thread);
        try
        {
            db.insert(DbStrings.UserThread.TABLE, null,cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }
    }

    public void saveManga(Manga manga)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.Manga.TITLE, manga.getTitle());
        cv.put(DbStrings.Manga.AUTHOR, manga.getAuthor());
        cv.put(DbStrings.Manga.YEAR, manga.getYear());
        cv.put(DbStrings.Manga.PUBLISHER, manga.getPublisher());
        cv.put(DbStrings.Manga.MAGAZINE, manga.getMagazine());
        cv.put(DbStrings.Manga.GENRE, manga.getGenre());
        cv.put(DbStrings.Manga.VOLUMES, manga.getVolumes());
        cv.put(DbStrings.Manga.CHAPTERS, manga.getChapters());
        cv.put(DbStrings.Manga.PLOT, manga.getPlot());
        cv.put(DbStrings.Manga.IMAGE, manga.getImg());
        try
        {
            db.insert(DbStrings.Manga.TABLE, null,cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }
    }

    public void saveUserManga(Integer id_user, Integer id_manga)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbStrings.UserManga.FIELD_ID_USER, id_user);
        cv.put(DbStrings.UserManga.FIELD_ID_MANGA, id_manga);
        cv.put(DbStrings.UserManga.FIELD_STATE_MANGA, 0);
        try
        {
            db.insert(DbStrings.UserManga.TABLE, null,cv);
        }
        catch (SQLiteException sqle)
        {
            System.out.println("erroreeeeeeeeeeee");
        }
    }


    public void deleteUserManga(Integer user_id, String title){
        ArrayList<Integer> list = getMangasIdByUserId(user_id);
        Integer manga_id=0;
        for (Integer id: list) {
            if(getMangaById(id).getTitle().equals(title))
                manga_id = id;
        }
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.delete(DbStrings.UserManga.TABLE, DbStrings.UserManga.FIELD_ID_USER + "=?" + " and " + DbStrings.UserManga.FIELD_ID_MANGA + "=?" ,new String[] {user_id.toString(),manga_id.toString()});
    }

    public void deleteUserThread(Integer id_user, Integer id_thread){
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.delete(DbStrings.UserThread.TABLE, DbStrings.UserThread.ID_USER + "=?" + " and " + DbStrings.UserThread.ID_THREAD + "=?" ,new String[] {id_user.toString(),id_thread.toString()});
    }

    public boolean findUserThread(Integer id_user, Integer id_thread){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.UserThread.TABLE, null, DbStrings.UserThread.ID_USER + "=?" + " and " + DbStrings.UserThread.ID_THREAD + "=?", new String[] {id_user.toString(),id_thread.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return false;

            return true;
        }
        catch (SQLiteException sqle) { System.out.println("erroreeeeeeeeeeee");}
        return false;
    }

    public Integer userLogin(String username, String psw)
    {
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.User.TABLE, null, DbStrings.User.USERNAME + "=?" , new String[] {username} , null, null, null, null);
            crs.moveToFirst();
            if(crs.getCount() == 0)
                return null;

            User user= cursorToUser(crs);

            if(user.getPassword().equals(psw))
                return user.getId();

        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;
    }


    public void updateState(Integer user_id, String title, Integer state){
        ArrayList<Integer> list = getMangasIdByUserId(user_id);
        Integer manga_id=0;
        for (Integer id: list) {
            if(getMangaById(id).getTitle().equals(title))
                manga_id = id;
        }

        ContentValues values = new ContentValues();
        values.put(DbStrings.UserManga.FIELD_ID_USER,user_id.toString());
        values.put(DbStrings.UserManga.FIELD_ID_MANGA,manga_id.toString());
        values.put(DbStrings.UserManga.FIELD_STATE_MANGA,state);

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.update(DbStrings.UserManga.TABLE,values, DbStrings.UserManga.FIELD_ID_USER + "=?" + " and " + DbStrings.UserManga.FIELD_ID_MANGA + "=?" ,new String[] {user_id.toString(),manga_id.toString()});
    }

    public void changeImage(Integer user_id, String img){
        ContentValues values = new ContentValues();
        values.put(DbStrings.User.IMAGE,img);

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.update(DbStrings.User.TABLE,values, DbStrings.User.FIELD_ID + "=?",new String[] {user_id.toString()});

    }

    public void changePassword(Integer user_id,String psw){
        ContentValues values = new ContentValues();
        values.put(DbStrings.User.PASSWORD,psw);

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.update(DbStrings.User.TABLE,values, DbStrings.User.FIELD_ID + "=?",new String[] {user_id.toString()});

    }

    public void changeEmail(Integer user_id,String email){
        ContentValues values = new ContentValues();
        values.put(DbStrings.User.EMAIL,email);

        SQLiteDatabase db=dbhelper.getReadableDatabase();
        db.update(DbStrings.User.TABLE,values, DbStrings.User.FIELD_ID + "=?",new String[] {user_id.toString()});

    }

    //restituisce tutti gli id dei manga della libreria di un utente passato come id
    public ArrayList<MangaState> getMangasStatesByUserId(Integer id_user){
        Cursor crs = null;
        ArrayList<MangaState> list = new ArrayList<>();
        String table = DbStrings.UserManga.TABLE + " userManga " +
                "inner join " + DbStrings.Manga.TABLE  + " manga "  +
                "on userManga.id_manga = manga._id";

        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(table, null, DbStrings.UserManga.FIELD_ID_USER + "=?" , new String[] {id_user.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return list;

            return cursorToMangasState(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){  System.err.println("sql errore"+e); }

        return list;
    }


    public ArrayList<Integer> getMangasIdByUserId(Integer id_user){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.UserManga.TABLE, null, DbStrings.UserManga.FIELD_ID_USER + "=?" , new String[] {id_user.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToMangasId(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){  System.err.println("sql errore"+e); }

        return null;
    }

    //restituisce lo stato di un manga attraverso il suo id e quello dell'utente a cui è riferito
    /*public String getStateByMangaId(Integer id_user, Integer id_manga){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.Libreria.TABLE, null, DbStrings.Libreria.FIELD_ID_USER + "=?" , new String[] {id_user.toString(), id_manga.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToMangasId(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){  System.err.println("sql errore"+e); }

        return null;

    }*/

    public User findUserByEmail(String email){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.User.TABLE, null, DbStrings.User.EMAIL + "=?" , new String[] {email} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToUser(crs);


        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;

    }


    public User findUserByUsername(String username){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.User.TABLE, null, DbStrings.User.USERNAME + "=?" , new String[] {username} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToUser(crs);

        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;
    }


    public User getUserById(Integer id){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.User.TABLE, null, DbStrings.User.FIELD_ID + "=?" , new String[] {id.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToUser(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;

    }

    public Integer findMangaByTitle(String title){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.Manga.TABLE, null, DbStrings.Manga.TITLE + "=?" , new String[] {title} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return crs.getInt(0);

        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;
    }

    public Manga getMangaById(Integer id){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DbStrings.Manga.TABLE, null, DbStrings.Manga.FIELD_ID + "=?" , new String[] {id.toString()} , null, null, null, null);
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToManga(crs);

        }
        catch(SQLiteException sqle)
        {
            System.err.println("sql errore");

        }
        catch(Exception e){ System.err.println("sql errore"+e);}

        return null;

    }

    public ArrayList<Manga> getAllMangas(){
        Cursor crs = null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs = db.rawQuery( "select * from "+DbStrings.Manga.TABLE, null );
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return null;

            return cursorToMangas(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println(" sql errore ");
        }
        catch(Exception e){ System.err.println(" sql errore "+e);}

        return null;
    }

    public ArrayList<Thread> getThreads(){
        Cursor crs = null;
        ArrayList<Thread> list = new ArrayList<>();
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs = db.rawQuery( "select * from "+DbStrings.Thread.TABLE, null );
            crs.moveToFirst();

            if(crs.getCount() == 0)
                return list;

            return cursorToThreads(crs);
        }
        catch(SQLiteException sqle)
        {
            System.err.println(" sql errore ");
        }
        catch(Exception e){ System.err.println(" sql errore "+e);}

        return list;
    }

    public static User cursorToUser(Cursor crs ){
        return  new User (crs.getInt(0), crs.getString(1),crs.getString(2),crs.getString(3),crs.getString(4));

    }

    public static ArrayList<Thread> cursorToThreads(Cursor crs ){
        ArrayList<Thread> threads = new ArrayList<>();
        Thread thread;
        while(!crs.isAfterLast()) {
            thread = cursorToThread(crs);
            threads.add(thread);
            crs.moveToNext();
        }
        return threads;
    }

    public static Thread cursorToThread(Cursor crs ){
        return  new Thread (crs.getInt(0), crs.getString(1),crs.getString(2),crs.getString(3),crs.getInt(4));
    }

    public static Manga cursorToManga(Cursor crs ){
        return  new Manga (crs.getInt(0), crs.getString(1),crs.getString(2), crs.getInt(3),crs.getString(4),crs.getString(5),crs.getString(6),crs.getInt(7),crs.getInt(8),crs.getString(9),crs.getString(10));
    }

    public static ArrayList<Manga> cursorToMangas(Cursor crs ){
        ArrayList<Manga> mangas = new ArrayList<>();
        Manga manga;
        while(!crs.isAfterLast()) {
            manga = cursorToManga(crs);
            mangas.add(manga);
            crs.moveToNext();
        }
        return mangas;
    }


    public static MangaState cursorToMangaState(Cursor crs ){
        return  new MangaState (crs.getInt(3), crs.getString(4),crs.getString(5),crs.getInt(6),crs.getString(7),crs.getString(8),crs.getString(9),crs.getInt(10),crs.getInt(11),crs.getString(12),crs.getString(13), crs.getInt(2));
    }

    public static ArrayList<MangaState> cursorToMangasState(Cursor crs ){
        ArrayList<MangaState> mangas = new ArrayList<>();
        MangaState manga;
        while(!crs.isAfterLast()) {
            manga = cursorToMangaState(crs);
            mangas.add(manga);
            crs.moveToNext();
        }
        return mangas;
    }


    //riceve un cursore che punta alla lista di manga di un utente e restituisce gli id di quei manga di quell utente in un array
    public static ArrayList<Integer> cursorToMangasId(Cursor crs ){
        ArrayList<Integer> mangasId = new ArrayList<>();
        while(!crs.isAfterLast()) {
            mangasId.add(crs.getInt(1));
            crs.moveToNext();
        }
        return mangasId;
    }


}

package it.polimi.ingsw.GC_06.Network.Client;


import it.polimi.ingsw.GC_06.Network.Message.MessageClient;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by massimo on 11/06/17.
 */
public class ClientOutputSocket implements ClientOutputHandler {

    private ObjectOutputStream objectOutputStream;
    private ExecutorService pool;
    PrintWriter output;

    public ClientOutputSocket()
    {
        super();
    }

    public ClientOutputSocket(ObjectOutputStream objectOutputStream) throws IOException {
        this.objectOutputStream = objectOutputStream;
        this.pool = Executors.newCachedThreadPool();
        this.output = new PrintWriter(new OutputStreamWriter(objectOutputStream));
    }

    @Override
    public void submit(MessageClient action) {
        pool.submit(() -> {
            //Serializzazione e invio

        /*    RuntimeTypeAdapterFactory typeAdapterFactory = RuntimeTypeAdapterFactory.of(MessageClient.class, "type").registerSubtype(ChangeStatus.class).registerSubtype(BoardActionOnTower.class);
            Gson gson=new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(typeAdapterFactory).create();

                output.println(gson.toJson(action));
                output.flush();*/
        });
    }

    public void finish() throws IOException {
        output.close();
    }
}

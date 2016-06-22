package test.java.com.capitalone.checkwordster.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.standalone.CommandLineOptions;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsLoader;
import us.monoid.json.JSONObject;
import us.monoid.web.JSONResource;
import us.monoid.web.Resty;

import java.net.URI;
import java.net.URL;

import static us.monoid.web.Resty.content;

public class CheckWordsterClient {
    private String numberInWords;

    public CheckWordsterClient(String numberInDigits, String whichServer) throws Exception {
        Resty resty;

        resty = new Resty();

        WireMockServer wireMockServer = null;

        URL url = null;
        URI uri;
        if (whichServer.equals("local")) {
            url = new URL("http://localhost:9090/checkWordster");
        } else if (whichServer.equals("fake")) {
            FileSource fileSource=new SingleRootFileSource("./wiremock");
//            FileSource filesFileSource=fileSource.child("__files");
            FileSource mappingsFileSource=fileSource.child("mappings");

            CommandLineOptions options=new CommandLineOptions();

            wireMockServer=new WireMockServer(Options.DEFAULT_PORT, fileSource, options.browserProxyingEnabled());
            wireMockServer.loadMappingsUsing(new JsonFileMappingsLoader(mappingsFileSource));

            wireMockServer.start();

            url = new URL("http://localhost:8080/checkWordster");
        }

        uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        String requestToPost = "{\"numberInDigits\": \"" + numberInDigits + "\"}";

        JSONResource response = resty.json(uri,content(new JSONObject(requestToPost)));
        numberInWords = (String) response.get("numberInWords");

        if (whichServer.equals("fake")) {
            wireMockServer.stop();
        }
    }

    public String getWords() throws Exception {
        return numberInWords;
    }

}
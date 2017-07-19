/**
 * Created by David et Monireh on 19/07/2017.
 */
import com.google.gson.GsonBuilder;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;

/**
 * Created by David on 09/04/2015.
 */
public class Translator {

    private final static String API_KEY = "YOUR_API_KEY";

    private static Translator instance;

    private String token;

    private TranslatorTokenAPI api;

    public Translator(String token, TranslatorTokenAPI api) {
        this.token = token;
        this.api = api;
    }

    public static Translator get() {
        if (instance == null) {
            try {
                instance = new Translator(getToken(API_KEY), new Retrofit.Builder()
                        .baseUrl("https://api.microsofttranslator.com/v2/http.svc/")
                        .addConverterFactory(SimpleXmlConverterFactory.create())
                        .build()
                        .create(TranslatorTokenAPI.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private static String getToken(String apiKey) throws IOException {
        TranslatorTokenAPI translatorTokenAPI = new Retrofit.Builder()
                .baseUrl("https://api.cognitive.microsoft.com/sts/v1.0/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build()
                .create(TranslatorTokenAPI.class);
        Response<String> response = translatorTokenAPI.getToken(apiKey).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("no token found");
        }
    }

    public Languages getLanguages() {
        try {
            Response<Languages> response = api.languages("Bearer " + instance.token).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("problem on languages request : " + response.message() + " : " + response.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String translateFile(String filePath, String from, String to) {
        String[] lines = FileReaderUtils.readFile(filePath);
        String translatedString = "";
        for (String line : lines) {
            translatedString += translate(line, from, to);
            translatedString += "\n";
        }
        return translatedString;
    }

    public void translateAppStrings(String input, String from, String separator, String output) {
        String[] rawStrings = FileReaderUtils.readLine(input, separator);
        String separatorIntern = ";";
        StringBuilder sb = new StringBuilder();
        for (String locale : Main.LOCALE_STRINGS_FOR_STORE_BING) {
            sb.append(locale + separatorIntern);
            for (String rawString : rawStrings) {
                sb.append(translate(rawString, from, locale));
                sb.append(separatorIntern);
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        FileReaderUtils.writeToFile(sb, output);
    }

    public String translate(String text, String from, String to, String category) {
        try {
            Response<String> response = api.translate("Bearer " + instance.token, "text/plain", text, from, to, category).execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new IOException("problem on translate request : " + response.message() + " : " + response.errorBody().string() + " to paramter : " + to);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String translate(String text, String from, String to) {
        return translate(text, from, to, "general");
    }

//    public static String detectLanguage(String text) {
//        HttpClient client = new DefaultHttpClient();
//        String url = null;
//        url = BASE_URL + "Detect";
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
//        nameValuePairs.add(new BasicNameValuePair("text", "This"));
//        String parameters = URLEncodedUtils.format(nameValuePairs, "utf-8");
//        HttpPost post = new HttpPost(url + "?" + parameters);
//        System.out.println(url + "?" + parameters);
//        try {
//            post.addHeader("Authorization", "Bearer " + token);
//            org.apache.http.HttpResponse response = client.execute(post);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            StringBuffer buffer = new StringBuffer();
//            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//                buffer.append(line);
//            }
//            return buffer.toString();
//            //JSONObject json = new JSONObject(buffer.toString());
//            //return json.getString("access_token");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}


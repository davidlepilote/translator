import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.*;

/**
 * Created by David et Monireh on 19/07/2017.
 */
@Root
public class Languages {
    @ElementList(entry = "string", inline = true)
    private ArrayList<String> stringNames;

    private List<Locale> locales = new ArrayList<>();

    private Map<Locale, String> mSLanguages = new HashMap<>();

    public List<Locale> getLanguages(){
        if(mSLanguages.size() == 0){
            for (String stringName : stringNames) {
                mSLanguages.put(Locale.forLanguageTag(stringName), stringName);
            }
        }
        List<Locale> languages = new ArrayList<>();
        languages.addAll(mSLanguages.keySet());
        return languages;
    }

    /**
     * Returns the Microsoft Translate API language to pass to an API request, according to a Locale
     * @param locale
     * @return
     */
    public String getMSLanguage(Locale locale){
        //Locale languageLocale = new Locale.Builder().setLanguage(locale.getLanguage()).build();
        if(mSLanguages.size() == 0){
            for (String stringName : stringNames) {
                mSLanguages.put(Locale.forLanguageTag(stringName), stringName);
            }
        }
        return mSLanguages.get(locale);
    }
}


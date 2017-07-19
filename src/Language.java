import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by David et Monireh on 19/07/2017.
 */
public enum Language {
    Afrikaans("Afrikaans", "af"),
    Amharique("Amharique", "am"),
    Anglais("Anglais", "en-IN"),
    Arménien("Arménien", "hy-AM"),
    Azéri("Azéri", "az-AZ"),
    Basque("Basque", "eu-ES"),
    Biélorusse("Biélorusse", "be"),
    Birman("Birman", "my-MM"),
    ChinoisHK("Chinois (Hong Kong)", "zh-HK"),
    Cinghalais("Cinghalais", "si-LK"),
    Filipino("Filipino", "fil"),
    Galicien("Galicien", "gl-ES"),
    Géorgien("Géorgien", "ka-GE"),
    Islandais("Islandais", "is-IS"),
    Kannada("Kannada", "kn-IN"),
    Khmer("Khmer", "km-KH"),
    Kirghize("Kirghize", "ky-KG"),
    Lao("Lao", "lo-LA"),
    Macédonien("Macédonien", "mk-MK"),
    Malayalam("Malayalam", "ml-IN"),
    Marathe("Marathe", "mr-IN"),
    Mongol("Mongol", "mn-MN"),
    Népalais("Népalais", "ne-NP"),
    Romanche("Romanche", "rm"),
    Swahili("Swahili", "sw"),
    Télougou("Télougou", "te-IN"),
    Zoulou("Zoulou", "zu"),
    Allemand("Allemand", "de-DE"),
    AnglaisAU("Anglais", "en-AU"),
    AnglaisUK("Anglais (Royaume-Uni)", "en-GB"),
    Arabe("Arabe", "ar"),
    Bengali("Bengali", "bn-BD"),
    Bulgare("Bulgare", "bg"),
    Catalan("Catalan", "ca"),
    ChinoisSimplifie("Chinois (simplifié)", "zh-CN"),
    ChinoisTraditionnel("Chinois (traditionnel)", "zh-TW"),
    Coréen("Coréen (Corée du Sud)", "ko-KR"),
    Croate("Croate", "hr"),
    Danois("Danois", "da-DK"),
    EspagnolLatin("Espagnol (Amérique latine)", "es-419"),
    EspagnolEspagne("Espagnol (Espagne)", "es-ES"),
    EspagnolUSA("Espagnol (États-Unis)", "es-US"),
    Estonien("Estonien", "et"),
    Finnois("Finnois", "fi-FI"),
    Français("Français", "fr-FR"),
    FrançaisCA("Français (Canada)", "fr-CA"),
    Grec("Grec", "el-GR"),
    Hébreu("Hébreu", "iw-IL"),
    Hindi("Hindi", "hi-IN"),
    Hongrois("Hongrois", "hu-HU"),
    Indonésien("Indonésien", "id"),
    Italien("Italien", "it-IT"),
    Japonais("Japonais", "ja-JP"),
    Letton("Letton", "lv"),
    Lituanien("Lituanien", "lt"),
    Malais("Malais", "ms"),
    Néerlandais("Néerlandais", "nl-NL"),
    Norvégien("Norvégien", "no-NO"),
    Persan("Persan", "fa"),
    Polonais("Polonais", "pl-PL"),
    PortugaisBR("Portugais (Brésil)", "pt-BR"),
    PortugaisPT("Portugais (Portugal)", "pt-PT"),
    Roumain("Roumain", "ro"),
    Russe("Russe", "ru-RU"),
    Serbe("Serbe", "sr"),
    Slovaque("Slovaque", "sk"),
    Slovène("Slovène", "sl"),
    Suédois("Suédois", "sv-SE"),
    Tamoul("Tamoul", "ta-IN"),
    Tchèque("Tchèque", "cs-CZ"),
    Thaï("Thaï", "th"),
    Turc("Turc", "tr-TR"),
    Ukrainien("Ukrainien", "uk"),
    Vietnamien("Vietnamien", "vi");

    private static Map<String, Language> lookup = new HashMap<>();

    static {
        for (Language language : Language.values()) {
            lookup.put(Locale.forLanguageTag(language.languageISO).getLanguage(), language);
        }
    }

    public static boolean exists(String language) {
        return lookup.containsKey(language);
    }

    public final String language;

    public final String languageISO;

    public final Locale locale;

    Language(String language, String languageISO) {
        this.language = language;
        this.languageISO = languageISO;
        this.locale = Locale.forLanguageTag(this.languageISO);
    }
}
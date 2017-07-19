import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by David et Monireh on 19/07/2017.
 */
public class Main {

    public static final String[] LOCALE_STRINGS_FOR_STORE_BING = new String[]{
            "en-US", "de-DE", "en-AU", "en-GB", "ar", "bg", "bn-BD", "ca", "zh-CN", "zh-TW",
            "ko-KR", "hr", "da-DK", "es-419", "es-ES", "es-US", "et", "fi-FI", "fr-FR",
            "fr-CA", "el-GR", "iw-IL", "hi-IN", "hu-HU", "id", "it-IT", "ja-JP", "lv",
            "lt", "ms", "nl-NL", "no-NO", "fa", "pl-PL", "pt-BR", "pt-PT", "ro", "ru-RU",
            "sr", "sk", "sl", "sv-SE", "cs-CZ", "th", "tr-TR", "uk", "vi"};

    public static void main(String args[]) throws Exception {
        translateIOSStrings("en", "res/Localizable.strings");
    }

    private static void translateIOSStrings(String fromLanguage, String path) throws IOException {
        final Path originalPath = Paths.get(path);

        final Path originalFileName = originalPath.getFileName();

        final Map<String, String> originalStrings = Files.readAllLines(originalPath)
                .stream()
                .filter(s -> !s.startsWith("/"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1].trim().replaceAll("[\";]", "")));

        // Get all languages that are translatable via MS Translate API
        Languages languages = Translator.get().getLanguages();

        for (Locale locale : languages.getLanguages()) {
            // First check that the language is supported in Play Store
            String language = locale.getLanguage();
            if (Language.exists(language)) {
                final HashMap<String, String> translatedStrings = new HashMap<>(originalStrings);
                for (String key : originalStrings.keySet()) {
                    translatedStrings.put(key, Translator.get().translate(originalStrings.get(key), fromLanguage, languages.getMSLanguage(locale)));
                }
                final String destinationPath = new File(path).getParentFile().getAbsolutePath() + "\\" + language + ".lproj";
                File translatedStringsDirectory = new File(destinationPath);
                translatedStringsDirectory.mkdir();
                translatedStringsDirectory.setWritable(true);

                Files.write(Paths.get(destinationPath + "\\" + originalFileName), translatedStrings.entrySet()
                        .stream()
                        .map(entry -> entry.getKey() + "= \"" + entry.getValue() + "\";")
                        .collect(Collectors.toList()));
            }
        }
    }
}

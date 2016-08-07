package com.siziksu.explorer.common.files;

import com.siziksu.explorer.R;

public class MimeTypes {

    public static final String PLAIN_TEXT = ".+\\.(txt)$";

    public static final String CODE = ".+\\.(html?|json|csv|java|pas|php.+|c|cpp|" + "bas|python|js|javascript|scala|xml|kml|css|ps|xslt?|tpl|tsv|bash|cmd|pl|pm|ps1|ps1xml|psc1|psd1|psm1|" + "py|pyc|pyo|r|rb|sdl|sh|tcl|vbs|xpl|ada|adb|ads|clj|cls|cob|cbl|cxx|cs|csproj|d|e|el|go|h|hpp|hxx|l|m)$";

    /**
     * http://en.wikipedia.org/wiki/Image_file_formats
     */
    public static final String IMAGE = ".+\\.(gif|jpe?g|png|tiff?|wmf|emf|jfif|exif|" + "raw|bmp|ppm|pgm|pbm|pnm|webp|riff|tga|ilbm|img|pcx|ecw|sid|cd5|fits|pgf|xcf|svg|pns|jps|icon?|" + "jp2|mng|xpm|djvu)$";

    /**
     * http://en.wikipedia.org/wiki/Audio_file_format
     * http://en.wikipedia.org/wiki/List_of_file_formats
     */
    public static final String AUDIO = ".+\\.(mp[2-3]+|wav|aiff|au|m4a|ogg|raw|flac|" + "mid|amr|aac|alac|atrac|awb|m4p|mmf|mpc|ra|rm|tta|vox|wma)$";

    /**
     * http://en.wikipedia.org/wiki/Video_file_formats
     */
    public static final String VIDEO = ".+\\.(mp[4]+|flv|wmv|webm|m4v|3gp|mkv|mov|mpe?g|rmv?|ogv)$";

    /**
     * http://en.wikipedia.org/wiki/List_of_file_formats
     */
    public static final String COMPRESSED = ".+\\.(zip|7z|lz?|[jrt]ar|gz|gzip|bzip|xz|cab|sfx|" + "z|iso|bz?|rz|s7z|apk|dmg)$";

    public static int fileIcon(String file) {
        if (file.matches(MimeTypes.AUDIO)) {
            return R.drawable.system_audio;
        }
        if (file.matches(MimeTypes.VIDEO)) {
            return R.drawable.system_video;
        }
        if (file.matches(MimeTypes.IMAGE)) {
            return R.drawable.system_image;
        }
        if (file.matches(MimeTypes.COMPRESSED)) {
            return R.drawable.system_compressed;
        }
        if (file.matches(MimeTypes.PLAIN_TEXT)) {
            return R.drawable.system_txt;
        }
        if (file.matches(MimeTypes.CODE)) {
            return R.drawable.system_code;
        }
        return R.drawable.system_file;
    }
}

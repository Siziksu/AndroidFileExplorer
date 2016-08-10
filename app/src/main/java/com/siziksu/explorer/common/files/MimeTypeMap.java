package com.siziksu.explorer.common.files;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class MimeTypeMap {

    private Map<String, String> mimeTypeMap = new HashMap<>();

    private static MimeTypeMap instance;

    private MimeTypeMap() {
        loadEntries();
    }

    public static void init() {
        if (instance == null) {
            instance = new MimeTypeMap();
        }
    }

    public static MimeTypeMap get() {
        if (instance == null) {
            throw new RuntimeException("This class must be initialized");
        }
        return instance;
    }

    public String getFileExtensionFromUrl(String url) {
        if (url != null && url.length() > 0) {
            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }
            int filenamePos = url.lastIndexOf('/');
            String filename = 0 <= filenamePos ? url.substring(filenamePos + 1) : url;
            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (filename.length() > 0 &&
                Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }
        return "";
    }

    public String getMimeTypeFromExtension(String extension) {
        String mimeType;
        if (extension != null && extension.length() > 0) {
            mimeType = mimeTypeMap.get(extension.toLowerCase(Locale.getDefault()));
        } else {
            mimeType = "application/octet-stream";
        }
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }

    public boolean hasExtension(String extension) {
        return extension != null && extension.length() > 0 && mimeTypeMap.containsKey(extension);
    }

    public boolean hasMimeType(String mimeType) {
        return mimeType != null && mimeType.length() > 0 && mimeTypeMap.containsValue(mimeType);
    }

    private void loadEntries() {
        mimeTypeMap.put("323", "text/h323");
        mimeTypeMap.put("3g2", "video/3gpp");
        mimeTypeMap.put("3ga", "audio/3gpp");
        mimeTypeMap.put("3gp", "video/3gpp");
        mimeTypeMap.put("3gpp", "audio/3gpp");
        mimeTypeMap.put("7z", "application/x-7z-compressed");
        mimeTypeMap.put("aaf", "application/octet-stream");
        mimeTypeMap.put("abw", "application/x-abiword");
        mimeTypeMap.put("aca", "application/octet-stream");
        mimeTypeMap.put("accdb", "application/msaccess");
        mimeTypeMap.put("accde", "application/msaccess");
        mimeTypeMap.put("accdt", "application/msaccess");
        mimeTypeMap.put("acx", "application/internet-property-stream");
        mimeTypeMap.put("afm", "application/octet-stream");
        mimeTypeMap.put("ai", "application/postscript");
        mimeTypeMap.put("aif", "audio/x-aiff");
        mimeTypeMap.put("aifc", "audio/x-aiff");
        mimeTypeMap.put("aiff", "audio/x-aiff");
        mimeTypeMap.put("amr", "audio/amr");
        mimeTypeMap.put("apk", "application/vnd.android.package-archive");
        mimeTypeMap.put("application", "application/x-ms-application");
        mimeTypeMap.put("art", "image/x-jg");
        mimeTypeMap.put("asc", "text/plain");
        mimeTypeMap.put("asd", "application/octet-stream");
        mimeTypeMap.put("asf", "video/x-ms-asf");
        mimeTypeMap.put("asi", "application/octet-stream");
        mimeTypeMap.put("asm", "text/plain");
        mimeTypeMap.put("asr", "video/x-ms-asf");
        mimeTypeMap.put("asx", "video/x-ms-asf");
        mimeTypeMap.put("atom", "application/atom+xml");
        mimeTypeMap.put("au", "audio/basic");
        mimeTypeMap.put("avi", "video/x-msvideo");
        mimeTypeMap.put("axs", "application/olescript");
        mimeTypeMap.put("bas", "text/plain");
        mimeTypeMap.put("bcpio", "application/x-bcpio");
        mimeTypeMap.put("bin", "application/octet-stream");
        mimeTypeMap.put("bib", "text/x-bibtex");
        mimeTypeMap.put("bmp", "image/x-ms-bmp");
        mimeTypeMap.put("boo", "text/x-boo");
        mimeTypeMap.put("book", "application/x-maker");
        mimeTypeMap.put("c", "text/x-csrc");
        mimeTypeMap.put("c++", "text/x-c++src");
        mimeTypeMap.put("cab", "application/octet-stream");
        mimeTypeMap.put("calx", "application/vnd.ms-office.calx");
        mimeTypeMap.put("cat", "application/vnd.ms-pki.seccat");
        mimeTypeMap.put("cdf", "application/x-cdf");
        mimeTypeMap.put("cdr", "image/x-coreldraw");
        mimeTypeMap.put("cdt", "image/x-coreldrawtemplate");
        mimeTypeMap.put("cdy", "application/vnd.cinderella");
        mimeTypeMap.put("chm", "application/octet-stream");
        mimeTypeMap.put("chrt", "application/x-kchart");
        mimeTypeMap.put("class", "application/x-java-applet");
        mimeTypeMap.put("clp", "application/x-msclip");
        mimeTypeMap.put("cls", "text/x-tex");
        mimeTypeMap.put("cmx", "image/x-cmx");
        mimeTypeMap.put("cnf", "text/plain");
        mimeTypeMap.put("cod", "image/cis-cod");
        mimeTypeMap.put("cpio", "application/x-cpio");
        mimeTypeMap.put("cpp", "text/x-c++src");
        mimeTypeMap.put("cpt", "image/x-corelphotopaint");
        mimeTypeMap.put("crd", "application/x-mscardfile");
        mimeTypeMap.put("crl", "application/pkix-crl");
        mimeTypeMap.put("crt", "application/x-x509-ca-cert");
        mimeTypeMap.put("csh", "application/x-csh");
        mimeTypeMap.put("css", "text/css");
        mimeTypeMap.put("csv", "text/csv");
        mimeTypeMap.put("cur", "image/ico");
        mimeTypeMap.put("cxx", "text/x-c++src");
        mimeTypeMap.put("d", "text/x-dsrc");
        mimeTypeMap.put("dcr", "application/x-director");
        mimeTypeMap.put("deb", "application/x-debian-package");
        mimeTypeMap.put("deploy", "application/octet-stream");
        mimeTypeMap.put("der", "application/x-x509-ca-cert");
        mimeTypeMap.put("dib", "image/bmp");
        mimeTypeMap.put("dif", "video/dv");
        mimeTypeMap.put("diff", "text/plain");
        mimeTypeMap.put("dir", "application/x-director");
        mimeTypeMap.put("disco", "text/xml");
        mimeTypeMap.put("djv", "image/vnd.djvu");
        mimeTypeMap.put("djvu", "image/vnd.djvu");
        mimeTypeMap.put("dl", "video/dl");
        mimeTypeMap.put("dll", "application/x-msdownload");
        mimeTypeMap.put("dll.config", "text/xml");
        mimeTypeMap.put("dlm", "text/dlm");
        mimeTypeMap.put("dmg", "application/x-apple-diskimage");
        mimeTypeMap.put("dms", "application/x-dms");
        mimeTypeMap.put("doc", "application/msword");
        mimeTypeMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
        mimeTypeMap.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        mimeTypeMap.put("dot", "application/msword");
        mimeTypeMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
        mimeTypeMap.put("dotx", "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
        mimeTypeMap.put("dsp", "application/octet-stream");
        mimeTypeMap.put("dtd", "text/xml");
        mimeTypeMap.put("dv", "video/dv");
        mimeTypeMap.put("dvi", "application/x-dvi");
        mimeTypeMap.put("dwf", "drawing/x-dwf");
        mimeTypeMap.put("dwp", "application/octet-stream");
        mimeTypeMap.put("dxr", "application/x-director");
        mimeTypeMap.put("eml", "message/rfc822");
        mimeTypeMap.put("emz", "application/octet-stream");
        mimeTypeMap.put("eot", "application/octet-stream");
        mimeTypeMap.put("eps", "application/postscript");
        mimeTypeMap.put("etx", "text/x-setext");
        mimeTypeMap.put("evy", "application/envoy");
        mimeTypeMap.put("exe", "application/octet-stream");
        mimeTypeMap.put("exe.config", "text/xml");
        mimeTypeMap.put("ez", "application/andrew-inset");
        mimeTypeMap.put("fb", "application/x-maker");
        mimeTypeMap.put("fbdoc", "application/x-maker");
        mimeTypeMap.put("fdf", "application/vnd.fdf");
        mimeTypeMap.put("fif", "application/fractals");
        mimeTypeMap.put("fig", "application/x-xfig");
        mimeTypeMap.put("fla", "application/octet-stream");
        mimeTypeMap.put("flac", "application/x-flac");
        mimeTypeMap.put("fli", "video/fli");
        mimeTypeMap.put("flr", "x-world/x-vrml");
        mimeTypeMap.put("flv", "video/x-flv");
        mimeTypeMap.put("frame", "application/x-maker");
        mimeTypeMap.put("frm", "application/x-maker");
        mimeTypeMap.put("gcd", "text/x-pcs-gcd");
        mimeTypeMap.put("gcf", "application/x-graphing-calculator");
        mimeTypeMap.put("gif", "image/gif");
        mimeTypeMap.put("gnumeric", "application/x-gnumeric");
        mimeTypeMap.put("gsf", "application/x-font");
        mimeTypeMap.put("gsm", "audio/x-gsm");
        mimeTypeMap.put("gtar", "application/x-gtar");
        mimeTypeMap.put("gz", "application/x-gzip");
        mimeTypeMap.put("gzip", "application/x-gzip");
        mimeTypeMap.put("h", "text/x-chdr");
        mimeTypeMap.put("h++", "text/x-c++hdr");
        mimeTypeMap.put("h", "text/plain");
        mimeTypeMap.put("hdf", "application/x-hdf");
        mimeTypeMap.put("hdml", "text/x-hdml");
        mimeTypeMap.put("hh", "text/x-c++hdr");
        mimeTypeMap.put("hhc", "application/x-oleobject");
        mimeTypeMap.put("hhk", "application/octet-stream");
        mimeTypeMap.put("hhp", "application/octet-stream");
        mimeTypeMap.put("hlp", "application/winhlp");
        mimeTypeMap.put("hpp", "text/x-c++hdr");
        mimeTypeMap.put("hqx", "application/mac-binhex40");
        mimeTypeMap.put("hs", "text/x-haskell");
        mimeTypeMap.put("hta", "application/hta");
        mimeTypeMap.put("htc", "text/x-component");
        mimeTypeMap.put("htm", "text/html");
        mimeTypeMap.put("html", "text/html");
        mimeTypeMap.put("htt", "text/webviewhtml");
        mimeTypeMap.put("hxt", "text/html");
        mimeTypeMap.put("hxx", "text/x-c++hdr");
        mimeTypeMap.put("ica", "application/x-ica");
        mimeTypeMap.put("ice", "x-conference/x-cooltalk");
        mimeTypeMap.put("ico", "image/x-icon");
        mimeTypeMap.put("ics", "text/calendar");
        mimeTypeMap.put("icz", "text/calendar");
        mimeTypeMap.put("ief", "image/ief");
        mimeTypeMap.put("iges", "model/iges");
        mimeTypeMap.put("igs", "model/iges");
        mimeTypeMap.put("iii", "application/x-iphone");
        mimeTypeMap.put("inf", "application/octet-stream");
        mimeTypeMap.put("ins", "application/x-internet-signup");
        mimeTypeMap.put("iso", "application/x-iso9660-image");
        mimeTypeMap.put("isp", "application/x-internet-signup");
        mimeTypeMap.put("IVF", "video/x-ivf");
        mimeTypeMap.put("jar", "application/java-archive");
        mimeTypeMap.put("java", "text/x-java");
        mimeTypeMap.put("jck", "application/liquidmotion");
        mimeTypeMap.put("jcz", "application/liquidmotion");
        mimeTypeMap.put("jfif", "image/pjpeg");
        mimeTypeMap.put("jmz", "application/x-jmol");
        mimeTypeMap.put("jng", "image/x-jng");
        mimeTypeMap.put("jpb", "application/octet-stream");
        mimeTypeMap.put("jpe", "image/jpeg");
        mimeTypeMap.put("jpeg", "image/jpeg");
        mimeTypeMap.put("jpg", "image/jpeg");
        mimeTypeMap.put("js", "application/x-javascript");
        mimeTypeMap.put("jsx", "text/jscript");
        mimeTypeMap.put("kar", "audio/midi");
        mimeTypeMap.put("key", "application/pgp-keys");
        mimeTypeMap.put("kil", "application/x-killustrator");
        mimeTypeMap.put("kpr", "application/x-kpresenter");
        mimeTypeMap.put("kpt", "application/x-kpresenter");
        mimeTypeMap.put("ksp", "application/x-kspread");
        mimeTypeMap.put("kwd", "application/x-kword");
        mimeTypeMap.put("kwt", "application/x-kword");
        mimeTypeMap.put("latex", "application/x-latex");
        mimeTypeMap.put("lha", "application/x-lha");
        mimeTypeMap.put("lhs", "text/x-literate-haskell");
        mimeTypeMap.put("lit", "application/x-ms-reader");
        mimeTypeMap.put("lpk", "application/octet-stream");
        mimeTypeMap.put("lsf", "video/x-la-asf");
        mimeTypeMap.put("lsx", "video/x-la-asf");
        mimeTypeMap.put("ltx", "text/x-tex");
        mimeTypeMap.put("lzh", "application/x-lzh");
        mimeTypeMap.put("lzh", "application/octet-stream");
        mimeTypeMap.put("lzx", "application/x-lzx");
        mimeTypeMap.put("m13", "application/x-msmediaview");
        mimeTypeMap.put("m14", "application/x-msmediaview");
        mimeTypeMap.put("m1v", "video/mpeg");
        mimeTypeMap.put("m3u", "audio/x-mpegurl");
        mimeTypeMap.put("m4a", "audio/mpeg");
        mimeTypeMap.put("m4v", "video/m4v");
        mimeTypeMap.put("maker", "application/x-maker");
        mimeTypeMap.put("man", "application/x-troff-man");
        mimeTypeMap.put("manifest", "application/x-ms-manifest");
        mimeTypeMap.put("map", "text/plain");
        mimeTypeMap.put("mdb", "application/x-msaccess");
        mimeTypeMap.put("mdp", "application/octet-stream");
        mimeTypeMap.put("me", "application/x-troff-me");
        mimeTypeMap.put("mesh", "model/mesh");
        mimeTypeMap.put("mht", "message/rfc822");
        mimeTypeMap.put("mhtml", "message/rfc822");
        mimeTypeMap.put("mif", "application/x-mif");
        mimeTypeMap.put("mid", "audio/midi");
        mimeTypeMap.put("midi", "audio/midi");
        mimeTypeMap.put("mix", "application/octet-stream");
        mimeTypeMap.put("mm", "application/x-freemind");
        mimeTypeMap.put("mmf", "application/vnd.smaf");
        mimeTypeMap.put("mml", "text/mathml");
        mimeTypeMap.put("mng", "video/x-mng");
        mimeTypeMap.put("mno", "text/xml");
        mimeTypeMap.put("mny", "application/x-msmoney");
        mimeTypeMap.put("moc", "text/x-moc");
        mimeTypeMap.put("mov", "video/quicktime");
        mimeTypeMap.put("movie", "video/x-sgi-movie");
        mimeTypeMap.put("mp2", "video/mpeg");
        mimeTypeMap.put("mp3", "audio/mpeg");
        mimeTypeMap.put("mp4", "video/mp4");
        mimeTypeMap.put("mpa", "video/mpeg");
        mimeTypeMap.put("mpe", "video/mpeg");
        mimeTypeMap.put("mpeg", "video/mpeg");
        mimeTypeMap.put("mpega", "audio/mpeg");
        mimeTypeMap.put("mpg", "video/mpeg");
        mimeTypeMap.put("mpga", "audio/mpeg");
        mimeTypeMap.put("mpp", "application/vnd.ms-project");
        mimeTypeMap.put("mpv2", "video/mpeg");
        mimeTypeMap.put("ms", "application/x-troff-ms");
        mimeTypeMap.put("msh", "model/mesh");
        mimeTypeMap.put("msi", "application/x-msi");
        mimeTypeMap.put("mso", "application/octet-stream");
        mimeTypeMap.put("mvb", "application/x-msmediaview");
        mimeTypeMap.put("mvc", "application/x-miva-compiled");
        mimeTypeMap.put("mxmf", "audio/mobile-xmf");
        mimeTypeMap.put("mxu", "video/vnd.mpegurl");
        mimeTypeMap.put("nb", "application/mathematica");
        mimeTypeMap.put("nc", "application/x-netcdf");
        mimeTypeMap.put("nsc", "video/x-ms-asf");
        mimeTypeMap.put("nwc", "application/x-nwc");
        mimeTypeMap.put("nws", "message/rfc822");
        mimeTypeMap.put("o", "application/x-object");
        mimeTypeMap.put("ocx", "application/octet-stream");
        mimeTypeMap.put("oda", "application/oda");
        mimeTypeMap.put("odb", "application/vnd.oasis.opendocument.database");
        mimeTypeMap.put("odc", "text/x-ms-odc");
        mimeTypeMap.put("odf", "application/vnd.oasis.opendocument.formula");
        mimeTypeMap.put("odg", "application/vnd.oasis.opendocument.graphics");
        mimeTypeMap.put("odi", "application/vnd.oasis.opendocument.image");
        mimeTypeMap.put("odm", "application/vnd.oasis.opendocument.text-master");
        mimeTypeMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        mimeTypeMap.put("odt", "application/vnd.oasis.opendocument.text");
        mimeTypeMap.put("ogg", "application/ogg");
        mimeTypeMap.put("one", "application/onenote");
        mimeTypeMap.put("onea", "application/onenote");
        mimeTypeMap.put("onetoc", "application/onenote");
        mimeTypeMap.put("onetoc2", "application/onenote");
        mimeTypeMap.put("onetmp", "application/onenote");
        mimeTypeMap.put("onepkg", "application/onenote");
        mimeTypeMap.put("osdx", "application/opensearchdescription+xml");
        mimeTypeMap.put("otg", "application/vnd.oasis.opendocument.graphics-template");
        mimeTypeMap.put("oth", "application/vnd.oasis.opendocument.text-web");
        mimeTypeMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
        mimeTypeMap.put("ott", "application/vnd.oasis.opendocument.text-template");
        mimeTypeMap.put("oza", "application/x-oz-application");
        mimeTypeMap.put("p", "text/x-pascal");
        mimeTypeMap.put("p10", "application/pkcs10");
        mimeTypeMap.put("p12", "application/x-pkcs12");
        mimeTypeMap.put("p7b", "application/x-pkcs7-certificates");
        mimeTypeMap.put("p7c", "application/pkcs7-mime");
        mimeTypeMap.put("p7m", "application/pkcs7-mime");
        mimeTypeMap.put("p7r", "application/x-pkcs7-certreqresp");
        mimeTypeMap.put("p7s", "application/pkcs7-signature");
        mimeTypeMap.put("pac", "application/x-ns-proxy-autoconfig");
        mimeTypeMap.put("pas", "text/x-pascal");
        mimeTypeMap.put("pat", "image/x-coreldrawpattern");
        mimeTypeMap.put("pbm", "image/x-portable-bitmap");
        mimeTypeMap.put("pcf", "application/x-font");
        mimeTypeMap.put("pcx", "image/pcx");
        mimeTypeMap.put("pcz", "application/octet-stream");
        mimeTypeMap.put("pdf", "application/pdf");
        mimeTypeMap.put("pfa", "application/x-font");
        mimeTypeMap.put("pfb", "application/x-font");
        mimeTypeMap.put("pfm", "application/octet-stream");
        mimeTypeMap.put("pfx", "application/x-pkcs12");
        mimeTypeMap.put("pgm", "image/x-portable-graymap");
        mimeTypeMap.put("pgn", "application/x-chess-pgn");
        mimeTypeMap.put("pgp", "application/pgp-signature");
        mimeTypeMap.put("phps", "text/text");
        mimeTypeMap.put("pko", "application/vnd.ms-pki.pko");
        mimeTypeMap.put("pls", "audio/x-scpls");
        mimeTypeMap.put("pma", "application/x-perfmon");
        mimeTypeMap.put("pmc", "application/x-perfmon");
        mimeTypeMap.put("pml", "application/x-perfmon");
        mimeTypeMap.put("pmr", "application/x-perfmon");
        mimeTypeMap.put("pmw", "application/x-perfmon");
        mimeTypeMap.put("png", "image/png");
        mimeTypeMap.put("pnm", "image/x-portable-anymap");
        mimeTypeMap.put("pnz", "image/png");
        mimeTypeMap.put("po", "text/plain");
        mimeTypeMap.put("pot", "application/vnd.ms-powerpoint");
        mimeTypeMap.put("potm", "application/vnd.ms-powerpoint.template.macroEnabled.12");
        mimeTypeMap.put("potx", "application/vnd.openxmlformats-officedocument.presentationml.template");
        mimeTypeMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
        mimeTypeMap.put("ppm", "image/x-portable-pixmap");
        mimeTypeMap.put("pps", "application/vnd.ms-powerpoint");
        mimeTypeMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
        mimeTypeMap.put("ppsx", "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
        mimeTypeMap.put("ppt", "application/vnd.ms-powerpoint");
        mimeTypeMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        mimeTypeMap.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        mimeTypeMap.put("prf", "application/pics-rules");
        mimeTypeMap.put("prm", "application/octet-stream");
        mimeTypeMap.put("prx", "application/octet-stream");
        mimeTypeMap.put("ps", "application/postscript");
        mimeTypeMap.put("psd", "image/x-photoshop");
        mimeTypeMap.put("psm", "application/octet-stream");
        mimeTypeMap.put("psp", "application/octet-stream");
        mimeTypeMap.put("pub", "application/x-mspublisher");
        mimeTypeMap.put("qt", "video/quicktime");
        mimeTypeMap.put("qtl", "application/x-quicktimeplayer");
        mimeTypeMap.put("qxd", "application/octet-stream");
        mimeTypeMap.put("ra", "audio/x-pn-realaudio");
        mimeTypeMap.put("ram", "audio/x-pn-realaudio");
        mimeTypeMap.put("rar", "application/x-rar-compressed");
        mimeTypeMap.put("ras", "image/x-cmu-raster");
        mimeTypeMap.put("rdf", "application/rdf+xml");
        mimeTypeMap.put("rf", "image/vnd.rn-realflash");
        mimeTypeMap.put("rgb", "image/x-rgb");
        mimeTypeMap.put("rm", "audio/x-pn-realaudio");
        mimeTypeMap.put("rmi", "audio/mid");
        mimeTypeMap.put("roff", "application/x-troff");
        mimeTypeMap.put("rpm", "audio/x-pn-realaudio-plugin");
        mimeTypeMap.put("rss", "application/rss+xml");
        mimeTypeMap.put("rtf", "text/rtf");
        mimeTypeMap.put("rtx", "text/richtext");
        mimeTypeMap.put("scd", "application/x-msschedule");
        mimeTypeMap.put("sct", "text/scriptlet");
        mimeTypeMap.put("sd2", "audio/x-sd2");
        mimeTypeMap.put("sda", "application/vnd.stardivision.draw");
        mimeTypeMap.put("sdc", "application/vnd.stardivision.calc");
        mimeTypeMap.put("sdd", "application/vnd.stardivision.impress");
        mimeTypeMap.put("sdp", "application/vnd.stardivision.impress");
        mimeTypeMap.put("sdw", "application/vnd.stardivision.writer");
        mimeTypeMap.put("sea", "application/octet-stream");
        mimeTypeMap.put("setpay", "application/set-payment-initiation");
        mimeTypeMap.put("setreg", "application/set-registration-initiation");
        mimeTypeMap.put("sgf", "application/x-go-sgf");
        mimeTypeMap.put("sgl", "application/vnd.stardivision.writer-global");
        mimeTypeMap.put("sgml", "text/sgml");
        mimeTypeMap.put("sh", "application/x-sh");
        mimeTypeMap.put("shar", "application/x-shar");
        mimeTypeMap.put("sid", "audio/prs.sid");
        mimeTypeMap.put("silo", "model/mesh");
        mimeTypeMap.put("sisx", "x-epoc/x-sisx-app");
        mimeTypeMap.put("sit", "application/x-stuffit");
        mimeTypeMap.put("skd", "application/x-koan");
        mimeTypeMap.put("skm", "application/x-koan");
        mimeTypeMap.put("skp", "application/x-koan");
        mimeTypeMap.put("skt", "application/x-koan");
        mimeTypeMap.put("sldm", "application/vnd.ms-powerpoint.slide.macroEnabled.12");
        mimeTypeMap.put("sldx", "application/vnd.openxmlformats-officedocument.presentationml.slide");
        mimeTypeMap.put("smd", "audio/x-smd");
        mimeTypeMap.put("smf", "application/vnd.stardivision.math");
        mimeTypeMap.put("smi", "application/octet-stream");
        mimeTypeMap.put("smx", "audio/x-smd");
        mimeTypeMap.put("smz", "audio/x-smd");
        mimeTypeMap.put("snd", "audio/basic");
        mimeTypeMap.put("snp", "application/octet-stream");
        mimeTypeMap.put("spc", "application/x-pkcs7-certificates");
        mimeTypeMap.put("spl", "application/x-futuresplash");
        mimeTypeMap.put("src", "application/x-wais-source");
        mimeTypeMap.put("ssm", "application/streamingmedia");
        mimeTypeMap.put("sst", "application/vnd.ms-pki.certstore");
        mimeTypeMap.put("stc", "application/vnd.sun.xml.calc.template");
        mimeTypeMap.put("std", "application/vnd.sun.xml.draw.template");
        mimeTypeMap.put("sti", "application/vnd.sun.xml.impress.template");
        mimeTypeMap.put("stl", "application/vnd.ms-pki.stl");
        mimeTypeMap.put("stw", "application/vnd.sun.xml.writer.template");
        mimeTypeMap.put("sty", "text/x-tex");
        mimeTypeMap.put("sv4cpio", "application/x-sv4cpio");
        mimeTypeMap.put("sv4crc", "application/x-sv4crc");
        mimeTypeMap.put("svg", "image/svg+xml");
        mimeTypeMap.put("svgz", "image/svg+xml");
        mimeTypeMap.put("swf", "application/x-shockwave-flash");
        mimeTypeMap.put("sxc", "application/vnd.sun.xml.calc");
        mimeTypeMap.put("sxd", "application/vnd.sun.xml.draw");
        mimeTypeMap.put("sxg", "application/vnd.sun.xml.writer.global");
        mimeTypeMap.put("sxi", "application/vnd.sun.xml.impress");
        mimeTypeMap.put("sxm", "application/vnd.sun.xml.math");
        mimeTypeMap.put("sxw", "application/vnd.sun.xml.writer");
        mimeTypeMap.put("t", "application/x-troff");
        mimeTypeMap.put("tar", "application/x-tar");
        mimeTypeMap.put("taz", "application/x-gtar");
        mimeTypeMap.put("tcl", "application/x-tcl");
        mimeTypeMap.put("tex", "application/x-tex");
        mimeTypeMap.put("texi", "application/x-texinfo");
        mimeTypeMap.put("texinfo", "application/x-texinfo");
        mimeTypeMap.put("text", "text/plain");
        mimeTypeMap.put("tgz", "application/x-compressed");
        mimeTypeMap.put("thmx", "application/vnd.ms-officetheme");
        mimeTypeMap.put("thn", "application/octet-stream");
        mimeTypeMap.put("tif", "image/tiff");
        mimeTypeMap.put("tiff", "image/tiff");
        mimeTypeMap.put("toc", "application/octet-stream");
        mimeTypeMap.put("torrent", "application/x-bittorrent");
        mimeTypeMap.put("tr", "application/x-troff");
        mimeTypeMap.put("trm", "application/x-msterminal");
        mimeTypeMap.put("ts", "text/texmacs");
        mimeTypeMap.put("tsp", "application/dsptype");
        mimeTypeMap.put("tsv", "text/tab-separated-values");
        mimeTypeMap.put("ttf", "application/octet-stream");
        mimeTypeMap.put("txt", "text/plain");
        mimeTypeMap.put("u32", "application/octet-stream");
        mimeTypeMap.put("udeb", "application/x-debian-package");
        mimeTypeMap.put("uls", "text/iuls");
        mimeTypeMap.put("ustar", "application/x-ustar");
        mimeTypeMap.put("vbs", "text/vbscript");
        mimeTypeMap.put("vcd", "application/x-cdlink");
        mimeTypeMap.put("vcf", "text/x-vcard");
        mimeTypeMap.put("vcs", "text/plain");
        mimeTypeMap.put("vdx", "application/vnd.ms-visio.viewer");
        mimeTypeMap.put("vml", "text/xml");
        mimeTypeMap.put("vob", "video/mpeg");
        mimeTypeMap.put("vor", "application/vnd.stardivision.writer");
        mimeTypeMap.put("vsd", "application/vnd.visio");
        mimeTypeMap.put("vss", "application/vnd.visio");
        mimeTypeMap.put("vst", "application/vnd.visio");
        mimeTypeMap.put("vsto", "application/x-ms-vsto");
        mimeTypeMap.put("vsw", "application/vnd.visio");
        mimeTypeMap.put("vsx", "application/vnd.visio");
        mimeTypeMap.put("vtx", "application/vnd.visio");
        mimeTypeMap.put("wad", "application/x-doom");
        mimeTypeMap.put("wav", "audio/x-wav");
        mimeTypeMap.put("wax", "audio/x-ms-wax");
        mimeTypeMap.put("wbmp", "image/vnd.wap.wbmp");
        mimeTypeMap.put("wcm", "application/vnd.ms-works");
        mimeTypeMap.put("wdb", "application/vnd.ms-works");
        mimeTypeMap.put("webarchive", "application/x-webarchive");
        mimeTypeMap.put("wks", "application/vnd.ms-works");
        mimeTypeMap.put("wm", "video/x-ms-wm");
        mimeTypeMap.put("wma", "audio/x-ms-wma");
        mimeTypeMap.put("wmd", "application/x-ms-wmd");
        mimeTypeMap.put("wmf", "application/x-msmetafile");
        mimeTypeMap.put("wml", "text/vnd.wap.wml");
        mimeTypeMap.put("wmlc", "application/vnd.wap.wmlc");
        mimeTypeMap.put("wmls", "text/vnd.wap.wmlscript");
        mimeTypeMap.put("wmlsc", "application/vnd.wap.wmlscriptc");
        mimeTypeMap.put("wmp", "video/x-ms-wmp");
        mimeTypeMap.put("wmv", "video/x-ms-wmv");
        mimeTypeMap.put("wmx", "video/x-ms-wmx");
        mimeTypeMap.put("wmz", "application/x-ms-wmz");
        mimeTypeMap.put("wps", "application/vnd.ms-works");
        mimeTypeMap.put("wri", "application/x-mswrite");
        mimeTypeMap.put("wrl", "x-world/x-vrml");
        mimeTypeMap.put("wrz", "x-world/x-vrml");
        mimeTypeMap.put("wsdl", "text/xml");
        mimeTypeMap.put("wvx", "video/x-ms-wvx");
        mimeTypeMap.put("wz", "application/x-wingz");
        mimeTypeMap.put("x", "application/directx");
        mimeTypeMap.put("xaf", "x-world/x-vrml");
        mimeTypeMap.put("xaml", "application/xaml+xml");
        mimeTypeMap.put("xap", "application/x-silverlight-app");
        mimeTypeMap.put("xbap", "application/x-ms-xbap");
        mimeTypeMap.put("xbm", "image/x-xbitmap");
        mimeTypeMap.put("xcf", "application/x-xcf");
        mimeTypeMap.put("xdr", "text/plain");
        mimeTypeMap.put("xht", "application/xhtml+xml");
        mimeTypeMap.put("xhtml", "application/xhtml+xml");
        mimeTypeMap.put("xla", "application/vnd.ms-excel");
        mimeTypeMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
        mimeTypeMap.put("xlc", "application/vnd.ms-excel");
        mimeTypeMap.put("xlm", "application/vnd.ms-excel");
        mimeTypeMap.put("xls", "application/vnd.ms-excel");
        mimeTypeMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
        mimeTypeMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
        mimeTypeMap.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        mimeTypeMap.put("xlt", "application/vnd.ms-excel");
        mimeTypeMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
        mimeTypeMap.put("xltx", "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
        mimeTypeMap.put("xlw", "application/vnd.ms-excel");
        mimeTypeMap.put("xmf", "audio/midi");
        mimeTypeMap.put("xml", "text/xml");
        mimeTypeMap.put("xof", "x-world/x-vrml");
        mimeTypeMap.put("xpm", "image/x-xpixmap");
        mimeTypeMap.put("xps", "application/vnd.ms-xpsdocument");
        mimeTypeMap.put("xsd", "text/xml");
        mimeTypeMap.put("xsf", "text/xml");
        mimeTypeMap.put("xsl", "text/xml");
        mimeTypeMap.put("xslt", "text/xml");
        mimeTypeMap.put("xsn", "application/octet-stream");
        mimeTypeMap.put("xtp", "application/octet-stream");
        mimeTypeMap.put("xwd", "image/x-xwindowdump");
        mimeTypeMap.put("z", "application/x-compress");
        mimeTypeMap.put("zip", "application/x-zip-compressed");
    }
}
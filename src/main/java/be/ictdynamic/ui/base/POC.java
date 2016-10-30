package be.ictdynamic.ui.base;

import be.ictdynamic.common.collections.CollectionUtilities;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.services.GoogleMapRealServiceImpl;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Class POC.
 *
 * @author Wim Van den Brande
 * @since 09/10/2016 - 15:57
 */
public class POC {
    static String[] gemeentes = new String[]{
            "AALST",
            "AALTER",
            "AARSCHOT",
            "AARTSELAAR",
            "AFFLIGEM",
            "ALKEN",
            "ALVERINGEM",
            "ANTWERPEN",
            "ANZEGEM",
            "ARDOOIE",
            "ARENDONK",
            "AS",
            "ASSE",
            "ASSENEDE",
            "AVELGEM",
            "BAARLE-HERTOG",
            "BALEN",
            "BEERNEM",
            "BEERSE",
            "BEERSEL",
            "BEGIJNENDIJK",
            "BEKKEVOORT",
            "BERINGEN",
            "BERLAAR",
            "BERLARE",
            "BERTEM",
            "BEVER",
            "BEVEREN",
            "BIERBEEK",
            "BILZEN",
            "BLANKENBERGE",
            "BOCHOLT",
            "BOECHOUT",
            "BONHEIDEN",
            "BOOM",
            "BOORTMEERBEEK",
            "BORGLOON",
            "BORNEM",
            "BORSBEEK",
            "BOUTERSEM",
            "BRAKEL",
            "BRASSCHAAT",
            "BRECHT",
            "BREDENE",
            "BREE",
            "BRUGGE",
            "BUGGENHOUT",
            "DAMME",
            "DE HAAN",
            "DE PANNE",
            "DE PINTE",
            "DEERLIJK",
            "DEINZE",
            "DENDERLEEUW",
            "DENDERMONDE",
            "DENTERGEM",
            "DESSEL",
            "DESTELBERGEN",
            "DIEPENBEEK",
            "DIEST",
            "DIKSMUIDE",
            "DILBEEK",
            "DILSEN-STOKKEM",
            "DROGENBOS",
            "DUFFEL",
            "EDEGEM",
            "EEKLO",
            "ERPE-MERE",
            "ESSEN",
            "EVERGEM",
            "GALMAARDEN",
            "GAVERE",
            "GEEL",
            "GEETBETS",
            "GENK",
            "GENT",
            "GERAARDSBERGEN",
            "GINGELOM",
            "GISTEL",
            "GLABBEEK",
            "GOOIK",
            "GRIMBERGEN",
            "GROBBENDONK",
            "HAACHT",
            "HAALTERT",
            "HALEN",
            "HALLE",
            "HAM",
            "HAMME",
            "HAMONT-ACHEL",
            "HARELBEKE",
            "HASSELT",
            "HECHTEL-EKSEL",
            "HEERS",
            "HEIST-OP-DEN-BERG",
            "HEMIKSEM",
            "HERENT",
            "HERENTALS",
            "HERENTHOUT",
            "HERK-DE-STAD",
            "HERNE",
            "HERSELT",
            "HERSTAPPE",
            "HERZELE",
            "HEUSDEN-ZOLDER",
            "HEUVELLAND",
            "HOEGAARDEN",
            "HOEILAART",
            "HOESELT",
            "HOLSBEEK",
            "HOOGLEDE",
            "HOOGSTRATEN",
            "HOREBEKE",
            "HOUTHALEN-HELCHTEREN",
            "HOUTHULST",
            "HOVE",
            "HULDENBERG",
            "HULSHOUT",
            "ICHTEGEM",
            "IEPER",
            "INGELMUNSTER",
            "IZEGEM",
            "JABBEKE",
            "KALMTHOUT",
            "KAMPENHOUT",
            "KAPELLEN",
            "KAPELLE-OP-DEN-BOS",
            "KAPRIJKE",
            "KASTERLEE",
            "KEERBERGEN",
            "KINROOI",
            "KLUISBERGEN",
            "KNESSELARE",
            "KNOKKE-HEIST",
            "KOEKELARE",
            "KOKSIJDE",
            "KONTICH",
            "KORTEMARK",
            "KORTENAKEN",
            "KORTENBERG",
            "KORTESSEM",
            "KORTRIJK",
            "KRAAINEM",
            "KRUIBEKE",
            "KRUISHOUTEM",
            "KUURNE",
            "LAAKDAL",
            "LAARNE",
            "LANAKEN",
            "LANDEN",
            "LANGEMARK-POELKAPELLE",
            "LEBBEKE",
            "LEDE",
            "LEDEGEM",
            "LENDELEDE",
            "LENNIK",
            "LEOPOLDSBURG",
            "LEUVEN",
            "LICHTERVELDE",
            "LIEDEKERKE",
            "LIER",
            "LIERDE",
            "LILLE",
            "LINKEBEEK",
            "LINT",
            "LINTER",
            "LOCHRISTI",
            "LOKEREN",
            "LOMMEL",
            "LONDERZEEL",
            "LO-RENINGE",
            "LOVENDEGEM",
            "LUBBEEK",
            "LUMMEN",
            "MAARKEDAL",
            "MAASEIK",
            "MAASMECHELEN",
            "MACHELEN",
            "MALDEGEM",
            "MALLE",
            "MECHELEN",
            "MEERHOUT",
            "MEEUWEN-GRUITRODE",
            "MEISE",
            "MELLE",
            "MENEN",
            "MERCHTEM",
            "MERELBEKE",
            "MERKSPLAS",
            "MESEN",
            "MEULEBEKE",
            "MIDDELKERKE",
            "MOERBEKE",
            "MOL",
            "MOORSLEDE",
            "MORTSEL",
            "NAZARETH",
            "NEERPELT",
            "NEVELE",
            "NIEL",
            "NIEUWERKERKEN",
            "NIEUWPOORT",
            "NIJLEN",
            "NINOVE",
            "OLEN",
            "OOSTENDE",
            "OOSTERZELE",
            "OOSTKAMP",
            "OOSTROZEBEKE",
            "OPGLABBEEK",
            "OPWIJK",
            "OUDENAARDE",
            "OUDENBURG",
            "OUD-HEVERLEE",
            "OUD-TURNHOUT",
            "OVERIJSE",
            "OVERPELT",
            "PEER",
            "PEPINGEN",
            "PITTEM",
            "POPERINGE",
            "PUTTE",
            "PUURS",
            "RANST",
            "RAVELS",
            "RETIE",
            "RIEMST",
            "RIJKEVORSEL",
            "ROESELARE",
            "RONSE",
            "ROOSDAAL",
            "ROTSELAAR",
            "RUISELEDE",
            "RUMST",
            "SCHELLE",
            "SCHERPENHEUVEL-ZICHEM",
            "SCHILDE",
            "SCHOTEN",
            "SINT-AMANDS",
            "SINT-GENESIUS-RODE",
            "SINT-GILLIS-WAAS",
            "SINT-KATELIJNE-WAVER",
            "SINT-LAUREINS",
            "SINT-LIEVENS-HOUTEM",
            "SINT-MARTENS-LATEM",
            "SINT-NIKLAAS",
            "SINT-PIETERS-LEEUW",
            "SINT-TRUIDEN",
            "SPIERE-HELKIJN",
            "STABROEK",
            "STADEN",
            "STEENOKKERZEEL",
            "STEKENE",
            "TEMSE",
            "TERNAT",
            "TERVUREN",
            "TESSENDERLO",
            "TIELT",
            "TIELT-WINGE",
            "TIENEN",
            "TONGEREN",
            "TORHOUT",
            "TREMELO",
            "TURNHOUT",
            "VEURNE",
            "VILVOORDE",
            "VLETEREN",
            "VOEREN",
            "VORSELAAR",
            "VOSSELAAR",
            "WAARSCHOOT",
            "WAASMUNSTER",
            "WACHTEBEKE",
            "WAREGEM",
            "WELLEN",
            "WEMMEL",
            "WERVIK",
            "WESTERLO",
            "WETTEREN",
            "WEVELGEM",
            "WEZEMBEEK-OPPEM",
            "WICHELEN",
            "WIELSBEKE",
            "WIJNEGEM",
            "WILLEBROEK",
            "WINGENE",
            "WOMMELGEM",
            "WORTEGEM-PETEGEM",
            "WUUSTWEZEL",
            "ZANDHOVEN",
            "ZAVENTEM",
            "ZEDELGEM",
            "ZELE",
            "ZELZATE",
            "ZEMST",
            "ZINGEM",
            "ZOERSEL",
            "ZOMERGEM",
            "ZONHOVEN",
            "ZONNEBEKE",
            "ZOTTEGEM",
            "ZOUTLEEUW",
            "ZUIENKERKE",
            "ZULTE",
            "ZUTENDAAL",
            "ZWALM",
            "ZWEVEGEM",
            "ZWIJNDRECHT"};

    private static final Logger LOG = LoggerFactory.getLogger(POC.class);

    public static void printGemeentes(String gemeenteFrom) {
        int totalNumEntriesProcessed = 0;

        int startCommuneIndex;
        for (startCommuneIndex = 0; startCommuneIndex < 308; startCommuneIndex++) {
            if (gemeentes[startCommuneIndex].equals(gemeenteFrom)) {
                break;
            }
        }

        if (startCommuneIndex == 308) {
            System.out.println(gemeenteFrom + " is een ongeldige gemeente");
            System.exit(-1);
        }

        for (int i = startCommuneIndex; i < 308 && totalNumEntriesProcessed < 2500; i++) {
            String gemeenteVan = gemeentes[i];

            GoogleMapRequest googleMapRequest = new GoogleMapRequest();
            googleMapRequest.setOfficeCommune(gemeenteVan);
            googleMapRequest.setOfficeCountry("België");

            int numCommunesProcessed = 0;
            for (int j = i + 1; j < 308 && totalNumEntriesProcessed < 2500; j++) {
                String gemeenteNaar = gemeentes[j];

                googleMapRequest.setHomeCommune(gemeenteNaar);
                googleMapRequest.setHomeCountry("België");
                GoogleMapResponse googleMapResponse;
                try {
                    Integer distance = 0;
                    Integer duration = 0;
                    GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
                    googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
                    distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
                    duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
//                    System.out.println(String.format("%s|%s|%d|%d", gemeenteVan, gemeenteNaar, distance, duration));
                    System.out.println(String.format("insert into commune_distance values ('%s', '%s', %d, %d);", gemeenteVan, gemeenteNaar, distance, duration));
                } catch (Exception e) {
                    LOG.error(">>>GoogleMapService is not available: exception = " + e);
                }
                totalNumEntriesProcessed += 1;
                numCommunesProcessed += 1;
            } // end second for

//            System.out.println(String.format("Aantal entries processed for gemeente %s: %03d", gemeenteVan, numCommunesProcessed));
        } // end first for

    }

    public static void printGemeente(String gemeenteToStartFrom) {
        int totalNumEntriesProcessed = 0;

        int startCommuneIndex;
        for (startCommuneIndex = 0; startCommuneIndex < 308; startCommuneIndex++) {
            if (gemeentes[startCommuneIndex].equals(gemeenteToStartFrom)) {
                break;
            }
        }

        if (startCommuneIndex == 308) {
            System.out.println(gemeenteToStartFrom + " is een ongeldige gemeente");
            System.exit(-1);
        }

        String gemeenteVan = gemeentes[startCommuneIndex];

        GoogleMapRequest googleMapRequest = new GoogleMapRequest();
        googleMapRequest.setOfficeCommune(gemeenteVan);
        googleMapRequest.setOfficeCountry("België");

        int numCommunesProcessed = 0;
        for (int j = startCommuneIndex + 1; j < 308 && totalNumEntriesProcessed < 2500; j++) {
            String gemeenteNaar = gemeentes[j];

            googleMapRequest.setHomeCommune(gemeenteNaar);
            googleMapRequest.setHomeCountry("België");
            GoogleMapResponse googleMapResponse;
            try {
                Integer distance = 0;
                Integer duration = 0;
                GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
                googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
                distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
                duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
                System.out.println(String.format("insert into commune_distance values ('%s', '%s', %d, %d);", gemeenteVan, gemeenteNaar, distance, duration));
            } catch (Exception e) {
                LOG.error(">>>GoogleMapService is not available: exception = " + e);
            }
            totalNumEntriesProcessed += 1;
            numCommunesProcessed += 1;
        } // end for

    }

    public static void printGemeenteFromGemeenteTo(String gemeenteFrom, String gemeenteTo) {
        int startCommuneIndex;
        for (startCommuneIndex = 0; startCommuneIndex < 308; startCommuneIndex++) {
            if (gemeentes[startCommuneIndex].equals(gemeenteFrom)) {
                break;
            }
        }

        if (startCommuneIndex == 308) {
            System.out.println(gemeenteFrom + " is een ongeldige gemeente");
            System.exit(-1);
        }

        String gemeenteVan = gemeentes[startCommuneIndex];

        int startCommuneIndex2;
        for (startCommuneIndex2 = 0; startCommuneIndex2 < 308; startCommuneIndex2++) {
            if (gemeentes[startCommuneIndex2].equals(gemeenteTo)) {
                break;
            }
        }

        if (startCommuneIndex2 == 308) {
            System.out.println(gemeenteTo + " is een ongeldige gemeente");
            System.exit(-1);
        }

        String gemeenteNaar = gemeentes[startCommuneIndex2];

        GoogleMapRequest googleMapRequest = new GoogleMapRequest();
        googleMapRequest.setOfficeCommune(gemeenteVan);
        googleMapRequest.setOfficeCountry("België");
        googleMapRequest.setHomeCommune(gemeenteNaar);
        googleMapRequest.setHomeCountry("België");
        GoogleMapResponse googleMapResponse;
        try {
            Integer distance = 0;
            Integer duration = 0;
            GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
            googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
            distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
            duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
            System.out.println(String.format("insert into commune_distance values ('%s', '%s', %d, %d);", gemeenteVan, gemeenteNaar, distance, duration));
        } catch (Exception e) {
            LOG.error(">>>GoogleMapService is not available: exception = " + e);
        }

    }

    public static void printGemeenteAdHoc() {

        Multimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("AS", "MERKSPLAS");
        multiMap.put("AS", "SINT-GILLIS-WAAS");
        multiMap.put("AS", "SINT-NIKLAAS")    ;
        multiMap.put("AS", "WAASMUNSTER")      ;

        multiMap.put("ASSE", "MERKSPLAS")       ;
        multiMap.put("ASSE", "SINT-GILLIS-WAAS") ;
        multiMap.put("ASSE", "SINT-NIKLAAS")      ;
        multiMap.put("ASSE", "WAASMUNSTER")        ;

        multiMap.put("BAARLE-HERTOG", "DEINZE")     ;
        multiMap.put("BAARLE-HERTOG", "TEMSE")       ;
        multiMap.put("BAARLE-HERTOG", "TERNAT")       ;
        multiMap.put("BAARLE-HERTOG", "TERVUREN")      ;
        multiMap.put("BAARLE-HERTOG", "TESSENDERLO")    ;
        multiMap.put("BAARLE-HERTOG", "TIELT")           ;
        multiMap.put("BAARLE-HERTOG", "TIELT-WINGE")      ;

        multiMap.put("BALEN", "ESSEN")                     ;
        multiMap.put("BALEN", "EVERGEM")                    ;
        multiMap.put("BALEN", "GALMAARDEN")                  ;
        multiMap.put("BALEN", "GAVERE")                       ;
        multiMap.put("BALEN", "SINT-AMANDS")                    ;
        multiMap.put("BALEN", "SINT-GENESIUS-RODE")            ;
        multiMap.put("BALEN", "SINT-GILLIS-WAAS")      ;
        multiMap.put("BALEN", "SINT-KATELIJNE-WAVER")   ;
        multiMap.put("BALEN", "SINT-LAUREINS")           ;
        multiMap.put("BALEN", "SINT-LIEVENS-HOUTEM")      ;
        multiMap.put("BALEN", "SINT-MARTENS-LATEM")        ;
        multiMap.put("BALEN", "SINT-NIKLAAS")               ;
        multiMap.put("BALEN", "SINT-PIETERS-LEEUW")          ;

        multiMap.put("BEERNEM", "GERAARDSBERGEN")   ;
        multiMap.put("BEERNEM", "GINGELOM")          ;
        multiMap.put("BEERNEM", "GISTEL")             ;
        multiMap.put("BEERNEM", "GLABBEEK")            ;
        multiMap.put("BEERNEM", "GOOIK")                ;
        multiMap.put("BEERNEM", "GRIMBERGEN")            ;
        multiMap.put("BEERNEM", "GROBBENDONK")            ;
        multiMap.put("BEERNEM", "MEISE")                   ;
        multiMap.put("BEERNEM", "MELLE")                    ;
        multiMap.put("BEERNEM", "MENEN")                     ;
        multiMap.put("BEERNEM", "MERCHTEM")                   ;
        multiMap.put("BEERNEM", "MERELBEKE")     ;
        multiMap.put("BEERNEM", "MERKSPLAS")      ;
        multiMap.put("BEERNEM", "MESEN")           ;
        multiMap.put("BEERNEM", "MEULEBEKE")            ;
        multiMap.put("BEERNEM", "MIDDELKERKE")      ;
        multiMap.put("BEERNEM", "MOERBEKE")          ;
        multiMap.put("BEERNEM", "MOL")               ;
        multiMap.put("BEERNEM", "ZEMST")              ;
        multiMap.put("BEERNEM", "ZINGEM")             ;
        multiMap.put("BEERNEM", "ZOERSEL")             ;
        multiMap.put("BEERNEM", "ZOMERGEM")             ;
        multiMap.put("BEERNEM", "ZONHOVEN")              ;
        multiMap.put("BEERNEM", "ZONNEBEKE")              ;
        multiMap.put("BEERNEM", "ZOTTEGEM")                ;
        multiMap.put("BEERNEM", "ZOUTLEEUW")                ;
        multiMap.put("BEERNEM", "ZUIENKERKE")                ;

        multiMap.put("BEERSE", "BEERSEL")        ;
        multiMap.put("BEERSE", "VOSSELAAR")       ;
        multiMap.put("BEERSE", "WAARSCHOOT")       ;
        multiMap.put("BEERSE", "WAASMUNSTER")        ;
        multiMap.put("BEERSE", "WACHTEBEKE")        ;
        multiMap.put("BEERSE", "WAREGEM")             ;
        multiMap.put("BEERSE", "WELLEN")               ;
        multiMap.put("BEERSE", "WEMMEL")                ;
        multiMap.put("BEERSE", "WERVIK")                 ;
        multiMap.put("BEERSE", "WESTERLO")                ;
        multiMap.put("BEERSE", "WETTEREN")                 ;

        for (Map.Entry entry : multiMap.entries()) {

            String k = (String) entry.getKey();
            String v = (String) entry.getValue();

            GoogleMapRequest googleMapRequest = new GoogleMapRequest();
            googleMapRequest.setOfficeCommune(v);
            googleMapRequest.setOfficeCountry("België");
            googleMapRequest.setHomeCommune(k);
            googleMapRequest.setHomeCountry("België");
            GoogleMapResponse googleMapResponse;
            try {
                Integer distance = 0;
                Integer duration = 0;
                GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
                googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
                distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
                duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
                System.out.println(String.format("insert into commune_distance values ('%s', '%s', %d, %d);", k, v, distance, duration));
            } catch (Exception e) {
                LOG.error(">>>GoogleMapService is not available: exception = " + e);
            }
        };

    }

    public static void printNotExists() {
        int startCommuneIndex;
        for (startCommuneIndex = 0; startCommuneIndex < 308; startCommuneIndex++) {
            System.out.println("select '" + gemeentes[startCommuneIndex] + "' from dual;");
            System.out.println("select commune_name \n" +
                    "from commune \n" +
                    "where not exists (select 1 from commune_distance where commune_name = commune_distance.commune_to and commune_from = '" + gemeentes[startCommuneIndex] + "' ) \n" +
                    "and commune_name > '" + gemeentes[startCommuneIndex] + "';");
        }
    }

    public static void main(String[] args) {
//        if (args.length != 1) {
//            System.out.println("invalid number of arguments. Expected: POC gemeente");
//            System.exit(-1);
//        }

//        printNotExists();

//        printGemeentes(args[0]);
//
        printGemeente("BEERSE");

//        printGemeenteAdHoc();

//        printGemeenteFromGemeenteTo(args[0], args[1]);
    }

}

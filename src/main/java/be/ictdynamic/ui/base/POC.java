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
                    Integer distance;
                    Integer duration;
                    GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
                    googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
                    distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
                    duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
                    System.out.println(String.format("insert into commune_distance values ('%s', '%s', %d, %d);", gemeenteVan, gemeenteNaar, distance, duration));

                    if (totalNumEntriesProcessed % 99 == 0) {
                        Thread.sleep(5000);
                    }

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

                if (totalNumEntriesProcessed % 99 == 0) {
                    Thread.sleep(5000);
                }

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

        multiMap.put("DAMME","MAASMECHELEN");
        multiMap.put("DAMME","MACHELEN");
        multiMap.put("DAMME","MALDEGEM");
        multiMap.put("DAMME","WEVELGEM");
        multiMap.put("DAMME","WEZEMBEEK-OPPEM");
        multiMap.put("DAMME","WICHELEN");
        multiMap.put("DAMME","WIELSBEKE");
        multiMap.put("DAMME","WIJNEGEM");
        multiMap.put("DAMME","WILLEBROEK");

        multiMap.put("DE HAAN", "KAPELLE-OP-DEN-BOS");
        multiMap.put("DE HAAN", "KAPRIJKE");
        multiMap.put("DE HAAN", "KASTERLEE");
        multiMap.put("DE HAAN", "KEERBERGEN");
        multiMap.put("DE HAAN", "KINROOI");
        multiMap.put("DE HAAN", "KLUISBERGEN");

        multiMap.put("DE PANNE", "DE PINTE");
        multiMap.put("DE PANNE", "DEERLIJK");
        multiMap.put("DE PANNE", "DEINZE");
        multiMap.put("DE PANNE", "DENDERLEEUW");
        multiMap.put("DE PANNE", "DENDERMONDE");
        multiMap.put("DE PANNE", "DENTERGEM");
        multiMap.put("DE PANNE", "DESSEL");
        multiMap.put("DE PANNE", "DESTELBERGEN");
        multiMap.put("DE PANNE", "DIEPENBEEK");
        multiMap.put("DE PANNE", "DIEST");
        multiMap.put("DE PANNE", "DIKSMUIDE");
        multiMap.put("DE PANNE", "DILBEEK");
        multiMap.put("DE PANNE", "DILSEN-STOKKEM");
        multiMap.put("DE PANNE", "DROGENBOS");
        multiMap.put("DE PANNE", "DUFFEL");
        multiMap.put("DE PANNE", "EDEGEM");
        multiMap.put("DE PANNE", "EEKLO");
        multiMap.put("DE PANNE", "ERPE-MERE");
        multiMap.put("DE PANNE", "ESSEN");
        multiMap.put("DE PANNE", "EVERGEM");
        multiMap.put("DE PANNE", "GALMAARDEN");
        multiMap.put("DE PANNE", "GAVERE");
        multiMap.put("DE PANNE", "GEEL");
        multiMap.put("DE PANNE", "GEETBETS");
        multiMap.put("DE PANNE", "GENK");
        multiMap.put("DE PANNE", "GENT");
        multiMap.put("DE PANNE", "GERAARDSBERGEN");
        multiMap.put("DE PANNE", "GINGELOM");
        multiMap.put("DE PANNE", "GISTEL");
        multiMap.put("DE PANNE", "GLABBEEK");
        multiMap.put("DE PANNE", "GOOIK");
        multiMap.put("DE PANNE", "GRIMBERGEN");
        multiMap.put("DE PANNE", "GROBBENDONK");
        multiMap.put("DE PANNE", "HAACHT");
        multiMap.put("DE PANNE", "HAALTERT");
        multiMap.put("DE PANNE", "HALEN");
        multiMap.put("DE PANNE", "HALLE");
        multiMap.put("DE PANNE", "HAM");
        multiMap.put("DE PANNE", "HAMME");
        multiMap.put("DE PANNE", "HAMONT-ACHEL");
        multiMap.put("DE PANNE", "HARELBEKE");
        multiMap.put("DE PANNE", "HASSELT");
        multiMap.put("DE PANNE", "HECHTEL-EKSEL");
        multiMap.put("DE PANNE", "HEERS");
        multiMap.put("DE PANNE", "HEIST-OP-DEN-BERG");
        multiMap.put("DE PANNE", "HEMIKSEM");
        multiMap.put("DE PANNE", "HERENT");
        multiMap.put("DE PANNE", "HERENTALS");
        multiMap.put("DE PANNE", "HERENTHOUT");
        multiMap.put("DE PANNE", "HERK-DE-STAD");
        multiMap.put("DE PANNE", "HERNE");
        multiMap.put("DE PANNE", "HERSELT");
        multiMap.put("DE PANNE", "HERSTAPPE");
        multiMap.put("DE PANNE", "HERZELE");
        multiMap.put("DE PANNE", "HEUSDEN-ZOLDER");
        multiMap.put("DE PANNE", "HEUVELLAND");
        multiMap.put("DE PANNE", "HOEGAARDEN");
        multiMap.put("DE PANNE", "HOEILAART");
        multiMap.put("DE PANNE", "HOESELT");
        multiMap.put("DE PANNE", "HOLSBEEK");
        multiMap.put("DE PANNE", "HOOGLEDE");
        multiMap.put("DE PANNE", "HOOGSTRATEN");
        multiMap.put("DE PANNE", "HOREBEKE");
        multiMap.put("DE PANNE", "HOUTHALEN-HELCHTEREN");
        multiMap.put("DE PANNE", "HOUTHULST");
        multiMap.put("DE PANNE", "HOVE");
        multiMap.put("DE PANNE", "HULDENBERG");
        multiMap.put("DE PANNE", "HULSHOUT");
        multiMap.put("DE PANNE", "ICHTEGEM");
        multiMap.put("DE PANNE", "IEPER");
        multiMap.put("DE PANNE", "INGELMUNSTER");
        multiMap.put("DE PANNE", "IZEGEM");
        multiMap.put("DE PANNE", "JABBEKE");
        multiMap.put("DE PANNE", "KALMTHOUT");
        multiMap.put("DE PANNE", "KAMPENHOUT");
        multiMap.put("DE PANNE", "KAPELLE-OP-DEN-BOS");
        multiMap.put("DE PANNE", "KAPELLEN");
        multiMap.put("DE PANNE", "KAPRIJKE");
        multiMap.put("DE PANNE", "KASTERLEE");
        multiMap.put("DE PANNE", "KEERBERGEN");
        multiMap.put("DE PANNE", "KINROOI");
        multiMap.put("DE PANNE", "KLUISBERGEN");
        multiMap.put("DE PANNE", "KNESSELARE");
        multiMap.put("DE PANNE", "KNOKKE-HEIST");
        multiMap.put("DE PANNE", "KOEKELARE");
        multiMap.put("DE PANNE", "KOKSIJDE");
        multiMap.put("DE PANNE", "KONTICH");
        multiMap.put("DE PANNE", "KORTEMARK");
        multiMap.put("DE PANNE", "KORTENAKEN");
        multiMap.put("DE PANNE", "KORTENBERG");
        multiMap.put("DE PANNE", "KORTESSEM");
        multiMap.put("DE PANNE", "KORTRIJK");
        multiMap.put("DE PANNE", "KRAAINEM");
        multiMap.put("DE PANNE", "KRUIBEKE");
        multiMap.put("DE PANNE", "KRUISHOUTEM");
        multiMap.put("DE PANNE", "KUURNE");
        multiMap.put("DE PANNE", "LAAKDAL");
        multiMap.put("DE PANNE", "LAARNE");
        multiMap.put("DE PANNE", "LANAKEN");
        multiMap.put("DE PANNE", "LANDEN");
        multiMap.put("DE PANNE", "LANGEMARK-POELKAPELLE");
        multiMap.put("DE PANNE", "LEBBEKE");
        multiMap.put("DE PANNE", "LEDE");
        multiMap.put("DE PANNE", "LEDEGEM");
        multiMap.put("DE PANNE", "LENDELEDE");
        multiMap.put("DE PANNE", "LENNIK");
        multiMap.put("DE PANNE", "LEOPOLDSBURG");
        multiMap.put("DE PANNE", "LEUVEN");
        multiMap.put("DE PANNE", "LICHTERVELDE");
        multiMap.put("DE PANNE", "LIEDEKERKE");
        multiMap.put("DE PANNE", "LIER");
        multiMap.put("DE PANNE", "LIERDE");
        multiMap.put("DE PANNE", "LILLE");
        multiMap.put("DE PANNE", "LINKEBEEK");
        multiMap.put("DE PANNE", "LINT");
        multiMap.put("DE PANNE", "LINTER");
        multiMap.put("DE PANNE", "LO-RENINGE");
        multiMap.put("DE PANNE", "LOCHRISTI");
        multiMap.put("DE PANNE", "LOKEREN");
        multiMap.put("DE PANNE", "LOMMEL");
        multiMap.put("DE PANNE", "LONDERZEEL");
        multiMap.put("DE PANNE", "LOVENDEGEM");
        multiMap.put("DE PANNE", "LUBBEEK");
        multiMap.put("DE PANNE", "LUMMEN");
        multiMap.put("DE PANNE", "MAARKEDAL");
        multiMap.put("DE PANNE", "MAASEIK");
        multiMap.put("DE PANNE", "MAASMECHELEN");
        multiMap.put("DE PANNE", "MACHELEN");
        multiMap.put("DE PANNE", "MALDEGEM");
        multiMap.put("DE PANNE", "MALLE");
        multiMap.put("DE PANNE", "MECHELEN");
        multiMap.put("DE PANNE", "MEERHOUT");
        multiMap.put("DE PANNE", "MEEUWEN-GRUITRODE");
        multiMap.put("DE PANNE", "MEISE");
        multiMap.put("DE PANNE", "MELLE");
        multiMap.put("DE PANNE", "MENEN");
        multiMap.put("DE PANNE", "MERCHTEM");
        multiMap.put("DE PANNE", "MERELBEKE");
        multiMap.put("DE PANNE", "MERKSPLAS");
        multiMap.put("DE PANNE", "MESEN");
        multiMap.put("DE PANNE", "MEULEBEKE");
        multiMap.put("DE PANNE", "MIDDELKERKE");
        multiMap.put("DE PANNE", "MOERBEKE");
        multiMap.put("DE PANNE", "MOL");
        multiMap.put("DE PANNE", "MOORSLEDE");
        multiMap.put("DE PANNE", "MORTSEL");
        multiMap.put("DE PANNE", "NAZARETH");
        multiMap.put("DE PANNE", "NEERPELT");
        multiMap.put("DE PANNE", "NEVELE");
        multiMap.put("DE PANNE", "NIEL");
        multiMap.put("DE PANNE", "NIEUWERKERKEN");
        multiMap.put("DE PANNE", "NIEUWPOORT");
        multiMap.put("DE PANNE", "NIJLEN");
        multiMap.put("DE PANNE", "NINOVE");
        multiMap.put("DE PANNE", "OLEN");
        multiMap.put("DE PANNE", "OOSTENDE");
        multiMap.put("DE PANNE", "OOSTERZELE");
        multiMap.put("DE PANNE", "OOSTKAMP");
        multiMap.put("DE PANNE", "OOSTROZEBEKE");
        multiMap.put("DE PANNE", "OPGLABBEEK");
        multiMap.put("DE PANNE", "OPWIJK");
        multiMap.put("DE PANNE", "OUD-HEVERLEE");
        multiMap.put("DE PANNE", "OUD-TURNHOUT");
        multiMap.put("DE PANNE", "OUDENAARDE");
        multiMap.put("DE PANNE", "OUDENBURG");
        multiMap.put("DE PANNE", "OVERIJSE");
        multiMap.put("DE PANNE", "OVERPELT");
        multiMap.put("DE PANNE", "PEER");
        multiMap.put("DE PANNE", "PEPINGEN");
        multiMap.put("DE PANNE", "PITTEM");
        multiMap.put("DE PANNE", "POPERINGE");
        multiMap.put("DE PANNE", "PUTTE");
        multiMap.put("DE PANNE", "PUURS");
        multiMap.put("DE PANNE", "RANST");
        multiMap.put("DE PANNE", "RAVELS");
        multiMap.put("DE PANNE", "RETIE");
        multiMap.put("DE PANNE", "RIEMST");
        multiMap.put("DE PANNE", "RIJKEVORSEL");
        multiMap.put("DE PANNE", "ROESELARE");
        multiMap.put("DE PANNE", "RONSE");
        multiMap.put("DE PANNE", "ROOSDAAL");
        multiMap.put("DE PANNE", "ROTSELAAR");
        multiMap.put("DE PANNE", "RUISELEDE");
        multiMap.put("DE PANNE", "RUMST");
        multiMap.put("DE PANNE", "SCHELLE");
        multiMap.put("DE PANNE", "SCHERPENHEUVEL-ZICHEM");
        multiMap.put("DE PANNE", "SCHILDE");
        multiMap.put("DE PANNE", "SCHOTEN");
        multiMap.put("DE PANNE", "SINT-AMANDS");
        multiMap.put("DE PANNE", "SINT-GENESIUS-RODE");
        multiMap.put("DE PANNE", "SINT-GILLIS-WAAS");
        multiMap.put("DE PANNE", "SINT-KATELIJNE-WAVER");
        multiMap.put("DE PANNE", "SINT-LAUREINS");
        multiMap.put("DE PANNE", "SINT-LIEVENS-HOUTEM");
        multiMap.put("DE PANNE", "SINT-MARTENS-LATEM");
        multiMap.put("DE PANNE", "SINT-NIKLAAS");
        multiMap.put("DE PANNE", "SINT-PIETERS-LEEUW");
        multiMap.put("DE PANNE", "SINT-TRUIDEN");
        multiMap.put("DE PANNE", "SPIERE-HELKIJN");
        multiMap.put("DE PANNE", "STABROEK");
        multiMap.put("DE PANNE", "STADEN");
        multiMap.put("DE PANNE", "STEENOKKERZEEL");
        multiMap.put("DE PANNE", "STEKENE");
        multiMap.put("DE PANNE", "TEMSE");
        multiMap.put("DE PANNE", "TERNAT");
        multiMap.put("DE PANNE", "TERVUREN");
        multiMap.put("DE PANNE", "TESSENDERLO");
        multiMap.put("DE PANNE", "TIELT");
        multiMap.put("DE PANNE", "TIELT-WINGE");
        multiMap.put("DE PANNE", "TIENEN");
        multiMap.put("DE PANNE", "TONGEREN");
        multiMap.put("DE PANNE", "TORHOUT");
        multiMap.put("DE PANNE", "TREMELO");
        multiMap.put("DE PANNE", "TURNHOUT");
        multiMap.put("DE PANNE", "VEURNE");
        multiMap.put("DE PANNE", "VILVOORDE");
        multiMap.put("DE PANNE", "VLETEREN");
        multiMap.put("DE PANNE", "VOEREN");
        multiMap.put("DE PANNE", "VORSELAAR");
        multiMap.put("DE PANNE", "VOSSELAAR");
        multiMap.put("DE PANNE", "WAARSCHOOT");
        multiMap.put("DE PANNE", "WAASMUNSTER");
        multiMap.put("DE PANNE", "WACHTEBEKE");
        multiMap.put("DE PANNE", "WAREGEM");
        multiMap.put("DE PANNE", "WELLEN");
        multiMap.put("DE PANNE", "WEMMEL");
        multiMap.put("DE PANNE", "WERVIK");
        multiMap.put("DE PANNE", "WESTERLO");
        multiMap.put("DE PANNE", "WETTEREN");
        multiMap.put("DE PANNE", "WEVELGEM");
        multiMap.put("DE PANNE", "WEZEMBEEK-OPPEM");
        multiMap.put("DE PANNE", "WICHELEN");
        multiMap.put("DE PANNE", "WIELSBEKE");
        multiMap.put("DE PANNE", "WIJNEGEM");
        multiMap.put("DE PANNE", "WILLEBROEK");
        multiMap.put("DE PANNE", "WINGENE");
        multiMap.put("DE PANNE", "WOMMELGEM");
        multiMap.put("DE PANNE", "WORTEGEM-PETEGEM");
        multiMap.put("DE PANNE", "WUUSTWEZEL");
        multiMap.put("DE PANNE", "ZANDHOVEN");
        multiMap.put("DE PANNE", "ZAVENTEM");
        multiMap.put("DE PANNE", "ZEDELGEM");
        multiMap.put("DE PANNE", "ZELE");
        multiMap.put("DE PANNE", "ZELZATE");
        multiMap.put("DE PANNE", "ZEMST");
        multiMap.put("DE PANNE", "ZINGEM");
        multiMap.put("DE PANNE", "ZOERSEL");
        multiMap.put("DE PANNE", "ZOMERGEM");
        multiMap.put("DE PANNE", "ZONHOVEN");
        multiMap.put("DE PANNE", "ZONNEBEKE");
        multiMap.put("DE PANNE", "ZOTTEGEM");
        multiMap.put("DE PANNE", "ZOUTLEEUW");
        multiMap.put("DE PANNE", "ZUIENKERKE");
        multiMap.put("DE PANNE", "ZULTE");
        multiMap.put("DE PANNE", "ZUTENDAAL");
        multiMap.put("DE PANNE", "ZWALM");
        multiMap.put("DE PANNE", "ZWEVEGEM");
        multiMap.put("DE PANNE", "ZWIJNDRECHT");

        multiMap.put("DIEST","OVERIJSE");
        multiMap.put("DIEST","OVERPELT");
        multiMap.put("DIEST","PEER");
        multiMap.put("DIEST","PEPINGEN");

        multiMap.put("DIKSMUIDE","GEEL");
        multiMap.put("DIKSMUIDE","GEETBETS");
        multiMap.put("DIKSMUIDE","GENK");
        multiMap.put("DIKSMUIDE","GENT");
        multiMap.put("DIKSMUIDE","GERAARDSBERGEN");
        multiMap.put("DIKSMUIDE","MACHELEN");
        multiMap.put("DIKSMUIDE","MALDEGEM");
        multiMap.put("DIKSMUIDE","MALLE");
        multiMap.put("DIKSMUIDE","WEZEMBEEK-OPPEM");
        multiMap.put("DIKSMUIDE","WICHELEN");
        multiMap.put("DIKSMUIDE","WIELSBEKE");
        multiMap.put("DIKSMUIDE","WIJNEGEM");
        multiMap.put("DIKSMUIDE","WILLEBROEK");
        multiMap.put("DIKSMUIDE","WINGENE");

        multiMap.put("DILBEEK","KORTESSEM");
        multiMap.put("DILBEEK","KORTRIJK");
        multiMap.put("DILBEEK","KRAAINEM");
        multiMap.put("DILBEEK","KRUIBEKE");
        multiMap.put("DILBEEK","KRUISHOUTEM");
        multiMap.put("DILBEEK","KUURNE");
        multiMap.put("DILBEEK","SINT-PIETERS-LEEUW");
        multiMap.put("DILBEEK","SINT-TRUIDEN");
        multiMap.put("DILBEEK","SPIERE-HELKIJN");
        multiMap.put("DILBEEK","STABROEK");
        multiMap.put("DILBEEK","STADEN");

        multiMap.put("DILSEN-STOKKEM","HOEGAARDEN");
        multiMap.put("DILSEN-STOKKEM","HOEILAART");
        multiMap.put("DILSEN-STOKKEM","HOESELT");
        multiMap.put("DILSEN-STOKKEM","HOLSBEEK");
        multiMap.put("DILSEN-STOKKEM","HOOGLEDE");
        multiMap.put("DILSEN-STOKKEM","OUD-HEVERLEE");
        multiMap.put("DILSEN-STOKKEM","OUD-TURNHOUT");
        multiMap.put("DILSEN-STOKKEM","OUDENAARDE");
        multiMap.put("DILSEN-STOKKEM","OUDENBURG");
        multiMap.put("DILSEN-STOKKEM","OVERIJSE");
        multiMap.put("DILSEN-STOKKEM","OVERPELT");

        multiMap.put("DROGENBOS","SINT-PIETERS-LEEUW");
        multiMap.put("DROGENBOS","SINT-TRUIDEN");
        multiMap.put("DROGENBOS","SPIERE-HELKIJN");
        multiMap.put("DROGENBOS","STABROEK");
        multiMap.put("DROGENBOS","STADEN");
        multiMap.put("DROGENBOS","STEENOKKERZEEL");

        multiMap.put("DUFFEL","HOLSBEEK");
        multiMap.put("DUFFEL","HOOGLEDE");
        multiMap.put("DUFFEL","HOOGSTRATEN");
        multiMap.put("DUFFEL","HOREBEKE");
        multiMap.put("DUFFEL","HOUTHALEN-HELCHTEREN");
        multiMap.put("DUFFEL","HOUTHULST");
        multiMap.put("DUFFEL","HOVE");
        multiMap.put("DUFFEL","HULDENBERG");
        multiMap.put("DUFFEL","HULSHOUT");
        multiMap.put("DUFFEL","ZWIJNDRECHT");

        multiMap.put("EDEGEM","EEKLO");
        multiMap.put("EDEGEM","ERPE-MERE");
        multiMap.put("EDEGEM","ESSEN");
        multiMap.put("EDEGEM","EVERGEM");
        multiMap.put("EDEGEM","GALMAARDEN");
        multiMap.put("EDEGEM","GAVERE");
        multiMap.put("EDEGEM","GEEL");
        multiMap.put("EDEGEM","GEETBETS");
        multiMap.put("EDEGEM","MAARKEDAL");
        multiMap.put("EDEGEM","MAASEIK");
        multiMap.put("EDEGEM","MAASMECHELEN");
        multiMap.put("EDEGEM","MACHELEN");
        multiMap.put("EDEGEM","MALDEGEM");
        multiMap.put("EDEGEM","MALLE");
        multiMap.put("EDEGEM","MECHELEN");
        multiMap.put("EDEGEM","MEERHOUT");
        multiMap.put("EDEGEM","MEEUWEN-GRUITRODE");
        multiMap.put("EDEGEM","WIJNEGEM");
        multiMap.put("EDEGEM","WILLEBROEK");
        multiMap.put("EDEGEM","WINGENE");
        multiMap.put("EDEGEM","WOMMELGEM");
        multiMap.put("EDEGEM","WORTEGEM-PETEGEM");
        multiMap.put("EDEGEM","WUUSTWEZEL");

        multiMap.put("EEKLO","LANAKEN");
        multiMap.put("EEKLO","LANDEN");
        multiMap.put("EEKLO","LANGEMARK-POELKAPELLE");
        multiMap.put("EEKLO","LEBBEKE");
        multiMap.put("EEKLO","LEDE");
        multiMap.put("EEKLO","LEDEGEM");
        multiMap.put("EEKLO","LENDELEDE");
        multiMap.put("EEKLO","TERVUREN");
        multiMap.put("EEKLO","TESSENDERLO");
        multiMap.put("EEKLO","TIELT");
        multiMap.put("EEKLO","TIELT-WINGE");
        multiMap.put("EEKLO","TIENEN");
        multiMap.put("EEKLO","TONGEREN");
        multiMap.put("EEKLO","TORHOUT");
        multiMap.put("EEKLO","TREMELO");
        multiMap.put("EEKLO","TURNHOUT");
        multiMap.put("EEKLO","VEURNE");

        multiMap.put("ERPE-MERE","KAPELLE-OP-DEN-BOS");
        multiMap.put("ERPE-MERE","KAPELLEN");
        multiMap.put("ERPE-MERE","KAPRIJKE");
        multiMap.put("ERPE-MERE","ROESELARE");
        multiMap.put("ERPE-MERE","RONSE");
        multiMap.put("ERPE-MERE","ROOSDAAL");
        multiMap.put("ERPE-MERE","ROTSELAAR");
        multiMap.put("ERPE-MERE","RUISELEDE");
        multiMap.put("ERPE-MERE","RUMST");
        multiMap.put("ERPE-MERE","SCHELLE");



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
            System.out.println("select 'multiMap.put(\""  + gemeentes[startCommuneIndex] + "\",' from dual;");
            System.out.println("select '\"' || commune_name || '\");' \n" +
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

        printGemeentes(args[0]);

//        printGemeente("HOVE");

//        printGemeenteAdHoc();

//        printGemeenteFromGemeenteTo(args[0], args[1]);
    }

}

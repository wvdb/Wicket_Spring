package be.ictdynamic.ui.base;

import be.ictdynamic.common.collections.CollectionUtilities;
import be.ictdynamic.domain.GoogleMapRequest;
import be.ictdynamic.domain.GoogleMapResponse;
import be.ictdynamic.services.GoogleMapRealServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static void printGemeentes(String gemeenteToStartFrom) {
        int numEntriesProcessed = 7;

        int dummy;
        for (dummy = 0; dummy < 308; dummy++) {
            if (gemeentes[dummy].equals(gemeenteToStartFrom)) {
               break;
            }
        }

        if (dummy==308) {
            System.out.println(gemeenteToStartFrom + " is een ongeldige gemeente");
            System.exit(-1);
        }

        for (int i = dummy; i < dummy+1; i++) {
            String gemeenteVan = gemeentes[i];

//        for (String gemeenteVan : gemeentes) {
//        String gemeenteVan = gemeentes[0];
            System.out.println("gemeente = " + gemeenteVan);

            GoogleMapRequest googleMapRequest = new GoogleMapRequest();
            googleMapRequest.setOfficeCommune(gemeenteVan);
            googleMapRequest.setOfficeCountry("België");

            for (int j = dummy; j < 308; j++) {
                String gemeenteNaar = gemeentes[j];

                googleMapRequest.setHomeCommune(gemeenteNaar);
                googleMapRequest.setHomeCountry("België");
                GoogleMapResponse googleMapResponse;
                try {
                    GoogleMapRealServiceImpl googleMapRealService = new GoogleMapRealServiceImpl();
                    googleMapResponse = googleMapRealService.getGoogleDistance(googleMapRequest);
                    Integer distance = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDistance();
                    Integer duration = CollectionUtilities.firstElement(googleMapResponse.getVoyages()) == null ? 0 : CollectionUtilities.firstElement(googleMapResponse.getVoyages()).getVoyageDuration();
                    System.out.println(String.format("%s|%s|%d|%d", gemeenteVan, gemeenteNaar, distance, duration));
                }
                catch (Exception e) {
                    LOG.error(">>>GoogleMapService is not available: exception = " + e);
                }
            } // end second for

            System.out.println(String.format("Aantal entries processed for gemeente %s: %03d", gemeenteVan, 308 - dummy));
            numEntriesProcessed += 1;
        } // end first for

    }

    public static void main(String[] args) {
        printGemeentes(args[0]);
    }

}

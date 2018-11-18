package io.diaryofrifat.code.utils.helper

import java.util.*

object CountryUtils {

    /**
     * Fields
     */
    private val mCountryCodeMap: MutableMap<String, String> = TreeMap()
    private val mCountryNameMap: MutableMap<String, String> = TreeMap()
    private val mCountryKeyMap: MutableMap<String, String> = TreeMap()

    init {
        mCountryCodeMap["AF"] = "+93"
        mCountryCodeMap["AL"] = "+355"
        mCountryCodeMap["DZ"] = "+213"
        mCountryCodeMap["AS"] = "+1684"
        mCountryCodeMap["AD"] = "+376"
        mCountryCodeMap["AO"] = "+244"
        mCountryCodeMap["AI"] = "+1264"
        mCountryCodeMap["AG"] = "+1268"
        mCountryCodeMap["AR"] = "+54"
        mCountryCodeMap["AM"] = "+374"
        mCountryCodeMap["AW"] = "+297"
        mCountryCodeMap["AU"] = "+61"
        mCountryCodeMap["AT"] = "+43"
        mCountryCodeMap["AZ"] = "+994"
        mCountryCodeMap["BS"] = "+1242"
        mCountryCodeMap["BH"] = "+973"
        mCountryCodeMap["BD"] = "+880"
        mCountryCodeMap["BB"] = "+1246"
        mCountryCodeMap["BY"] = "+375"
        mCountryCodeMap["BE"] = "+32"
        mCountryCodeMap["BZ"] = "+501"
        mCountryCodeMap["BJ"] = "+229"
        mCountryCodeMap["BM"] = "+1441"
        mCountryCodeMap["BT"] = "+975"
        mCountryCodeMap["BO"] = "+591"
        mCountryCodeMap["BA"] = "+387"
        mCountryCodeMap["BW"] = "+267"
        mCountryCodeMap["BR"] = "+55"
        mCountryCodeMap["IO"] = "+246"
        mCountryCodeMap["VG"] = "+1284"
        mCountryCodeMap["BN"] = "+673"
        mCountryCodeMap["BG"] = "+359"
        mCountryCodeMap["BF"] = "+226"
        mCountryCodeMap["BI"] = "+257"
        mCountryCodeMap["KH"] = "+855"
        mCountryCodeMap["CM"] = "+237"
        mCountryCodeMap["CA"] = "+1"
        mCountryCodeMap["CV"] = "+238"
        mCountryCodeMap["KY"] = "+345"
        mCountryCodeMap["CF"] = "+236"
        mCountryCodeMap["TD"] = "+235"
        mCountryCodeMap["CL"] = "+56"
        mCountryCodeMap["CN"] = "+86"
        mCountryCodeMap["CX"] = "+61"
        mCountryCodeMap["CO"] = "+57"
        mCountryCodeMap["KM"] = "+269"
        mCountryCodeMap["CG"] = "+242"
        mCountryCodeMap["CD"] = "+243"
        mCountryCodeMap["CK"] = "+682"
        mCountryCodeMap["CR"] = "+506"
        mCountryCodeMap["HR"] = "+385"
        mCountryCodeMap["CU"] = "+53"
        mCountryCodeMap["CW"] = "+599"
        mCountryCodeMap["CY"] = "+537"
        mCountryCodeMap["CZ"] = "+420"
        mCountryCodeMap["DK"] = "+45"
        mCountryCodeMap["DJ"] = "+253"
        mCountryCodeMap["DM"] = "+1767"
        mCountryCodeMap["DO"] = "+1809"
        mCountryCodeMap["EC"] = "+593"
        mCountryCodeMap["EG"] = "+20"
        mCountryCodeMap["SV"] = "+503"
        mCountryCodeMap["GQ"] = "+240"
        mCountryCodeMap["ER"] = "+291"
        mCountryCodeMap["EE"] = "+372"
        mCountryCodeMap["ET"] = "+251"
        mCountryCodeMap["FK"] = "+500"
        mCountryCodeMap["FO"] = "+298"
        mCountryCodeMap["FJ"] = "+679"
        mCountryCodeMap["FI"] = "+358"
        mCountryCodeMap["FR"] = "+33"
        mCountryCodeMap["GF"] = "+594"
        mCountryCodeMap["PF"] = "+689"
        mCountryCodeMap["GA"] = "+241"
        mCountryCodeMap["GM"] = "+220"
        mCountryCodeMap["GE"] = "+995"
        mCountryCodeMap["DE"] = "+49"
        mCountryCodeMap["GH"] = "+233"
        mCountryCodeMap["GI"] = "+350"
        mCountryCodeMap["GR"] = "+30"
        mCountryCodeMap["GL"] = "+299"
        mCountryCodeMap["GD"] = "+1473"
        mCountryCodeMap["GP"] = "+590"
        mCountryCodeMap["GU"] = "+1671"
        mCountryCodeMap["GT"] = "+502"
        mCountryCodeMap["GN"] = "+224"
        mCountryCodeMap["GW"] = "+245"
        mCountryCodeMap["GY"] = "+595"
        mCountryCodeMap["HT"] = "+509"
        mCountryCodeMap["HN"] = "+504"
        mCountryCodeMap["HK"] = "+852"
        mCountryCodeMap["HU"] = "+36"
        mCountryCodeMap["IS"] = "+354"
        mCountryCodeMap["IN"] = "+91"
        mCountryCodeMap["ID"] = "+62"
        mCountryCodeMap["IR"] = "+98"
        mCountryCodeMap["IQ"] = "+964"
        mCountryCodeMap["IE"] = "+353"
        mCountryCodeMap["IL"] = "+972"
        mCountryCodeMap["IT"] = "+39"
        mCountryCodeMap["CI"] = "+225"
        mCountryCodeMap["JM"] = "+1876"
        mCountryCodeMap["JP"] = "+81"
        mCountryCodeMap["JO"] = "+962"
        mCountryCodeMap["KZ"] = "+77"
        mCountryCodeMap["KE"] = "+254"
        mCountryCodeMap["KI"] = "+686"
        mCountryCodeMap["KW"] = "+965"
        mCountryCodeMap["KG"] = "+996"
        mCountryCodeMap["LA"] = "+856"
        mCountryCodeMap["LV"] = "+371"
        mCountryCodeMap["LB"] = "+961"
        mCountryCodeMap["LS"] = "+266"
        mCountryCodeMap["LR"] = "+231"
        mCountryCodeMap["LY"] = "+218"
        mCountryCodeMap["LI"] = "+423"
        mCountryCodeMap["LT"] = "+370"
        mCountryCodeMap["LU"] = "+352"
        mCountryCodeMap["MO"] = "+853"
        mCountryCodeMap["MK"] = "+389"
        mCountryCodeMap["MG"] = "+261"
        mCountryCodeMap["MW"] = "+265"
        mCountryCodeMap["MY"] = "+60"
        mCountryCodeMap["MV"] = "+960"
        mCountryCodeMap["ML"] = "+223"
        mCountryCodeMap["MT"] = "+356"
        mCountryCodeMap["MH"] = "+692"
        mCountryCodeMap["MQ"] = "+596"
        mCountryCodeMap["MR"] = "+222"
        mCountryCodeMap["MU"] = "+230"
        mCountryCodeMap["YT"] = "+262"
        mCountryCodeMap["MX"] = "+52"
        mCountryCodeMap["FM"] = "+691"
        mCountryCodeMap["MD"] = "+373"
        mCountryCodeMap["MC"] = "+377"
        mCountryCodeMap["MN"] = "+976"
        mCountryCodeMap["ME"] = "+382"
        mCountryCodeMap["MS"] = "+1664"
        mCountryCodeMap["MA"] = "+212"
        mCountryCodeMap["MM"] = "+95"
        mCountryCodeMap["NA"] = "+264"
        mCountryCodeMap["NR"] = "+674"
        mCountryCodeMap["NP"] = "+977"
        mCountryCodeMap["NL"] = "+31"
        mCountryCodeMap["KN"] = "+1"
        mCountryCodeMap["NC"] = "+687"
        mCountryCodeMap["NZ"] = "+64"
        mCountryCodeMap["NI"] = "+505"
        mCountryCodeMap["NE"] = "+227"
        mCountryCodeMap["NG"] = "+234"
        mCountryCodeMap["NU"] = "+683"
        mCountryCodeMap["NF"] = "+672"
        mCountryCodeMap["KP"] = "+850"
        mCountryCodeMap["MP"] = "+1670"
        mCountryCodeMap["NO"] = "+47"
        mCountryCodeMap["OM"] = "+968"
        mCountryCodeMap["PK"] = "+92"
        mCountryCodeMap["PW"] = "+680"
        mCountryCodeMap["PA"] = "+507"
        mCountryCodeMap["PG"] = "+675"
        mCountryCodeMap["PY"] = "+595"
        mCountryCodeMap["PE"] = "+51"
        mCountryCodeMap["PH"] = "+63"
        mCountryCodeMap["PL"] = "+48"
        mCountryCodeMap["PT"] = "+351"
        mCountryCodeMap["PR"] = "+1787"
        mCountryCodeMap["QA"] = "+974"
        mCountryCodeMap["RE"] = "+262"
        mCountryCodeMap["RO"] = "+40"
        mCountryCodeMap["RU"] = "+7"
        mCountryCodeMap["RW"] = "+250"
        mCountryCodeMap["WS"] = "+685"
        mCountryCodeMap["SM"] = "+378"
        mCountryCodeMap["SA"] = "+966"
        mCountryCodeMap["SN"] = "+221"
        mCountryCodeMap["RS"] = "+381"
        mCountryCodeMap["SC"] = "+248"
        mCountryCodeMap["SL"] = "+232"
        mCountryCodeMap["SG"] = "+65"
        mCountryCodeMap["SK"] = "+421"
        mCountryCodeMap["SI"] = "+386"
        mCountryCodeMap["SB"] = "+677"
        mCountryCodeMap["ZA"] = "+27"
        mCountryCodeMap["GS"] = "+500"
        mCountryCodeMap["KR"] = "+82"
        mCountryCodeMap["ES"] = "+34"
        mCountryCodeMap["LK"] = "+94"
        mCountryCodeMap["SD"] = "+249"
        mCountryCodeMap["SR"] = "+597"
        mCountryCodeMap["SZ"] = "+268"
        mCountryCodeMap["SE"] = "+46"
        mCountryCodeMap["CH"] = "+41"
        mCountryCodeMap["SY"] = "+963"
        mCountryCodeMap["TW"] = "+886"
        mCountryCodeMap["TJ"] = "+992"
        mCountryCodeMap["TZ"] = "+255"
        mCountryCodeMap["TH"] = "+66"
        mCountryCodeMap["TL"] = "+670"
        mCountryCodeMap["TG"] = "+228"
        mCountryCodeMap["TK"] = "+690"
        mCountryCodeMap["TO"] = "+676"
        mCountryCodeMap["TT"] = "+1868"
        mCountryCodeMap["TN"] = "+216"
        mCountryCodeMap["TR"] = "+90"
        mCountryCodeMap["TM"] = "+993"
        mCountryCodeMap["TC"] = "+1649"
        mCountryCodeMap["TV"] = "+688"
        mCountryCodeMap["UG"] = "+256"
        mCountryCodeMap["UA"] = "+380"
        mCountryCodeMap["AE"] = "+971"
        mCountryCodeMap["GB"] = "+44"
        mCountryCodeMap["US"] = "+1"
        mCountryCodeMap["UY"] = "+598"
        mCountryCodeMap["UZ"] = "+998"
        mCountryCodeMap["VU"] = "+678"
        mCountryCodeMap["VE"] = "+58"
        mCountryCodeMap["VN"] = "+84"
        mCountryCodeMap["WF"] = "+681"
        mCountryCodeMap["YE"] = "+967"
        mCountryCodeMap["ZM"] = "+260"
        mCountryCodeMap["ZW"] = "+263"
        mCountryCodeMap["SO"] = "+252"
        mCountryCodeMap["UK"] = "+44"

        mCountryNameMap[Country.ANDORRA] = "Andorra, Principality Of"
        mCountryNameMap[Country.UNITED_ARAB_EMIRATES] = "United Arab Emirates"
        mCountryNameMap[Country.AFGHANISTAN] = "Afghanistan, Islamic State Of"
        mCountryNameMap[Country.ANTIGUA_AND_BARBUDA] = "Antigua And Barbuda"
        mCountryNameMap[Country.ANGUILLA] = "Anguilla"
        mCountryNameMap[Country.ALBANIA] = "Albania"
        mCountryNameMap[Country.ARMENIA] = "Armenia"
        mCountryNameMap[Country.ANGOLA] = "Angola"
        mCountryNameMap[Country.ARGENTINA] = "Argentina"
        mCountryNameMap[Country.AMERICAN_SAMOA] = "American Samoa"
        mCountryNameMap[Country.AUSTRIA] = "Austria"
        mCountryNameMap[Country.AUSTRALIA] = "Australia"
        mCountryNameMap[Country.ARUBA] = "Aruba"
        mCountryNameMap[Country.AZERBAIDJAN] = "Azerbaidjan"
        mCountryNameMap[Country.BOSNIA_HERZEGOVINA] = "Bosnia-Herzegovina"
        mCountryNameMap[Country.BARBADOS] = "Barbados"
        mCountryNameMap[Country.BANGLADESH] = "Bangladesh"
        mCountryNameMap[Country.BELGIUM] = "Belgium"
        mCountryNameMap[Country.BURKINA_FASO] = "Burkina Faso"
        mCountryNameMap[Country.BULGARIA] = "Bulgaria"
        mCountryNameMap[Country.BAHRAIN] = "Bahrain"
        mCountryNameMap[Country.BURUNDI] = "Burundi"
        mCountryNameMap[Country.BENIN] = "Benin"
        mCountryNameMap[Country.BERMUDA] = "Bermuda"
        mCountryNameMap[Country.BRUNEI_DARUSSALAM] = "Brunei Darussalam"
        mCountryNameMap[Country.BOLIVIA] = "Bolivia"
        mCountryNameMap[Country.BRAZIL] = "Brazil"
        mCountryNameMap[Country.BAHAMAS] = "Bahamas"
        mCountryNameMap[Country.BHUTAN] = "Bhutan"
        mCountryNameMap[Country.BOTSWANA] = "Botswana"
        mCountryNameMap[Country.BELARUS] = "Belarus"
        mCountryNameMap[Country.BELIZE] = "Belize"
        mCountryNameMap[Country.CANADA] = "Canada"
        mCountryNameMap[Country.CENTRAL_AFRICAN_REPUBLIC] = "Central African Republic"
        mCountryNameMap[Country.CONGO_THE_DEMOCRATIC_REPUBLIC_OF_THE] = "Congo, The Democratic Republic Of The"
        mCountryNameMap[Country.CONGO] = "Congo"
        mCountryNameMap[Country.SWITZERLAND] = "Switzerland"
        mCountryNameMap[Country.IVORY_COAST] = "Ivory Coast (Cote D'Ivoire)"
        mCountryNameMap[Country.COOK_ISLANDS] = "Cook Islands"
        mCountryNameMap[Country.CHILE] = "Chile"
        mCountryNameMap[Country.CAMEROON] = "Cameroon"
        mCountryNameMap[Country.CHINA] = "China"
        mCountryNameMap[Country.COLOMBIA] = "Colombia"
        mCountryNameMap[Country.COSTA_RICA] = "Costa Rica"
        mCountryNameMap[Country.CUBA] = "Cuba"
        mCountryNameMap[Country.CAPE_VERDE] = "Cape Verde"
        mCountryNameMap[Country.CHRISTMAS_ISLAND] = "Christmas Island"
        mCountryNameMap[Country.CYPRUS] = "Cyprus"
        mCountryNameMap[Country.CZECH_REPUBLIC] = "Czech Republic"
        mCountryNameMap[Country.GERMANY] = "Germany"
        mCountryNameMap[Country.DJIBOUTI] = "Djibouti"
        mCountryNameMap[Country.DENMARK] = "Denmark"
        mCountryNameMap[Country.DOMINICA] = "Dominica"
        mCountryNameMap[Country.DOMINICAN_REPUBLIC] = "Dominican Republic"
        mCountryNameMap[Country.ALGERIA] = "Algeria"
        mCountryNameMap[Country.ECUADOR] = "Ecuador"
        mCountryNameMap[Country.ESTONIA] = "Estonia"
        mCountryNameMap[Country.EGYPT] = "Egypt"
        mCountryNameMap[Country.ERITREA] = "Eritrea"
        mCountryNameMap[Country.SPAIN] = "Spain"
        mCountryNameMap[Country.ETHIOPIA] = "Ethiopia"
        mCountryNameMap[Country.FINLAND] = "Finland"
        mCountryNameMap[Country.FIJI] = "Fiji"
        mCountryNameMap[Country.FALKLAND_ISLANDS] = "Falkland Islands"
        mCountryNameMap[Country.MICRONESIA] = "Micronesia"
        mCountryNameMap[Country.FAROE_ISLANDS] = "Faroe Islands"
        mCountryNameMap[Country.FRANCE] = "France"
        mCountryNameMap[Country.GABON] = "Gabon"
        mCountryNameMap[Country.GRENADA] = "Grenada"
        mCountryNameMap[Country.GEORGIA] = "Georgia"
        mCountryNameMap[Country.FRENCH_GUYANA] = "French Guyana"
        mCountryNameMap[Country.GHANA] = "Ghana"
        mCountryNameMap[Country.GIBRALTAR] = "Gibraltar"
        mCountryNameMap[Country.GREENLAND] = "Greenland"
        mCountryNameMap[Country.GAMBIA] = "Gambia"
        mCountryNameMap[Country.GUINEA] = "Guinea"
        mCountryNameMap[Country.GUADELOUPE_FRENCH] = "Guadeloupe (French)"
        mCountryNameMap[Country.EQUATORIAL_GUINEA] = "Equatorial Guinea"
        mCountryNameMap[Country.GREECE] = "Greece"
        mCountryNameMap[Country.S_GEORGIA_S_SANDWICH_ISLS] = "S. Georgia & S. Sandwich Isls."
        mCountryNameMap[Country.GUATEMALA] = "Guatemala"
        mCountryNameMap[Country.GUAM_USA] = "Guam (USA)"
        mCountryNameMap[Country.GUINEA_BISSAU] = "Guinea Bissau"
        mCountryNameMap[Country.GUYANA] = "Guyana"
        mCountryNameMap[Country.HONG_KONG] = "Hong Kong"
        mCountryNameMap[Country.HONDURAS] = "Honduras"
        mCountryNameMap[Country.CROATIA] = "Croatia"
        mCountryNameMap[Country.HAITI] = "Haiti"
        mCountryNameMap[Country.HUNGARY] = "Hungary"
        mCountryNameMap[Country.INDONESIA] = "Indonesia"
        mCountryNameMap[Country.IRELAND] = "Ireland"
        mCountryNameMap[Country.ISRAEL] = "Israel"
        mCountryNameMap[Country.INDIA] = "India"
        mCountryNameMap[Country.BRITISH_INDIAN_OCEAN_TERRITORY] = "British Indian Ocean Territory"
        mCountryNameMap[Country.IRAQ] = "Iraq"
        mCountryNameMap[Country.IRAN] = "Iran"
        mCountryNameMap[Country.ICELAND] = "Iceland"
        mCountryNameMap[Country.ITALY] = "Italy"
        mCountryNameMap[Country.JAMAICA] = "Jamaica"
        mCountryNameMap[Country.JORDAN] = "Jordan"
        mCountryNameMap[Country.JAPAN] = "Japan"
        mCountryNameMap[Country.KENYA] = "Kenya"
        mCountryNameMap[Country.KYRGYZ_REPUBLIC_KYRGYZSTAN] = "Kyrgyz Republic (Kyrgyzstan)"
        mCountryNameMap[Country.CAMBODIA_KINGDOM_OF] = "Cambodia, Kingdom Of"
        mCountryNameMap[Country.KIRIBATI] = "Kiribati"
        mCountryNameMap[Country.COMOROS] = "Comoros"
        mCountryNameMap[Country.SAINT_KITTS_NEVIS_ANGUILLA] = "Saint Kitts & Nevis Anguilla"
        mCountryNameMap[Country.NORTH_KOREA] = "North Korea"
        mCountryNameMap[Country.SOUTH_KOREA] = "South Korea"
        mCountryNameMap[Country.KUWAIT] = "Kuwait"
        mCountryNameMap[Country.CAYMAN_ISLANDS] = "Cayman Islands"
        mCountryNameMap[Country.KAZAKHSTAN] = "Kazakhstan"
        mCountryNameMap[Country.LAOS] = "Laos"
        mCountryNameMap[Country.LEBANON] = "Lebanon"
        mCountryNameMap[Country.LIECHTENSTEIN] = "Liechtenstein"
        mCountryNameMap[Country.SRILANKA] = "Srilanka"
        mCountryNameMap[Country.LIBERIA] = "Liberia"
        mCountryNameMap[Country.LESOTHO] = "Lesotho"
        mCountryNameMap[Country.LITHUANIA] = "Lithuania"
        mCountryNameMap[Country.LUXEMBOURG] = "Luxembourg"
        mCountryNameMap[Country.LATVIA] = "Latvia"
        mCountryNameMap[Country.LIBYA] = "Libya"
        mCountryNameMap[Country.MOROCCO] = "Morocco"
        mCountryNameMap[Country.MONACO] = "Monaco"
        mCountryNameMap[Country.MOLDAVIA] = "Moldavia"
        mCountryNameMap[Country.MADAGASCAR] = "Madagascar"
        mCountryNameMap[Country.MARSHALL_ISLANDS] = "Marshall Islands"
        mCountryNameMap[Country.MACEDONIA] = "Macedonia"
        mCountryNameMap[Country.MALI] = "Mali"
        mCountryNameMap[Country.MYANMAR] = "Myanmar"
        mCountryNameMap[Country.MONGOLIA] = "Mongolia"
        mCountryNameMap[Country.MACAU] = "Macau"
        mCountryNameMap[Country.NORTHERN_MARIANA_ISLANDS] = "Northern Mariana Islands"
        mCountryNameMap[Country.MARTINIQUE_FRENCH] = "Martinique (French)"
        mCountryNameMap[Country.MAURITANIA] = "Mauritania"
        mCountryNameMap[Country.MONTSERRAT] = "Montserrat"
        mCountryNameMap[Country.MALTA] = "Malta"
        mCountryNameMap[Country.MAURITIUS] = "Mauritius"
        mCountryNameMap[Country.MALDIVES] = "Maldives"
        mCountryNameMap[Country.MALAWI] = "Malawi"
        mCountryNameMap[Country.MEXICO] = "Mexico"
        mCountryNameMap[Country.MALAYSIA] = "Malaysia"
        mCountryNameMap[Country.NAMIBIA] = "Namibia"
        mCountryNameMap[Country.NEW_CALEDONIA_FRENCH] = "New Caledonia (French)"
        mCountryNameMap[Country.NIGER] = "Niger"
        mCountryNameMap[Country.NORFOLK_ISLAND] = "Norfolk Island"
        mCountryNameMap[Country.NIGERIA] = "Nigeria"
        mCountryNameMap[Country.NICARAGUA] = "Nicaragua"
        mCountryNameMap[Country.NETHERLANDS] = "Netherlands"
        mCountryNameMap[Country.NORWAY] = "Norway"
        mCountryNameMap[Country.NEPAL] = "Nepal"
        mCountryNameMap[Country.NAURU] = "Nauru"
        mCountryNameMap[Country.NIUE] = "Niue"
        mCountryNameMap[Country.NEW_ZEALAND] = "New Zealand"
        mCountryNameMap[Country.OMAN] = "Oman"
        mCountryNameMap[Country.PANAMA] = "Panama"
        mCountryNameMap[Country.PERU] = "Peru"
        mCountryNameMap[Country.POLYNESIA_FRENCH] = "Polynesia (French)"
        mCountryNameMap[Country.PAPUA_NEW_GUINEA] = "Papua New Guinea"
        mCountryNameMap[Country.PHILIPPINES] = "Philippines"
        mCountryNameMap[Country.PAKISTAN] = "Pakistan"
        mCountryNameMap[Country.POLAND] = "Poland"
        mCountryNameMap[Country.PUERTO_RICO] = "Puerto Rico"
        mCountryNameMap[Country.PORTUGAL] = "Portugal"
        mCountryNameMap[Country.PALAU] = "Palau"
        mCountryNameMap[Country.PARAGUAY] = "Paraguay"
        mCountryNameMap[Country.QATAR] = "Qatar"
        mCountryNameMap[Country.REUNION_FRENCH] = "Reunion (French)"
        mCountryNameMap[Country.ROMANIA] = "Romania"
        mCountryNameMap[Country.RUSSIAN_FEDERATION] = "Russian Federation"
        mCountryNameMap[Country.RWANDA] = "Rwanda"
        mCountryNameMap[Country.SAUDI_ARABIA] = "Saudi Arabia"
        mCountryNameMap[Country.SOLOMON_ISLANDS] = "Solomon Islands"
        mCountryNameMap[Country.SEYCHELLES] = "Seychelles"
        mCountryNameMap[Country.SUDAN] = "Sudan"
        mCountryNameMap[Country.SWEDEN] = "Sweden"
        mCountryNameMap[Country.SINGAPORE] = "Singapore"
        mCountryNameMap[Country.SLOVENIA] = "Slovenia"
        mCountryNameMap[Country.SLOVAK_REPUBLIC] = "Slovak Republic"
        mCountryNameMap[Country.SIERRA_LEONE] = "Sierra Leone"
        mCountryNameMap[Country.SAN_MARINO] = "San Marino"
        mCountryNameMap[Country.SENEGAL] = "Senegal"
        mCountryNameMap[Country.SOMALIA] = "Somalia"
        mCountryNameMap[Country.SURINAME] = "Suriname"
        mCountryNameMap[Country.EL_SALVADOR] = "El Salvador"
        mCountryNameMap[Country.SYRIA] = "Syria"
        mCountryNameMap[Country.SWAZILAND] = "Swaziland"
        mCountryNameMap[Country.TURKS_AND_CAICOS_ISLANDS] = "Turks And Caicos Islands"
        mCountryNameMap[Country.CHAD] = "Chad"
        mCountryNameMap[Country.TOGO] = "Togo"
        mCountryNameMap[Country.THAILAND] = "Thailand"
        mCountryNameMap[Country.TADJIKISTAN] = "Tadjikistan"
        mCountryNameMap[Country.TOKELAU] = "Tokelau"
        mCountryNameMap[Country.TURKMENISTAN] = "Turkmenistan"
        mCountryNameMap[Country.TUNISIA] = "Tunisia"
        mCountryNameMap[Country.TONGA] = "Tonga"
        mCountryNameMap[Country.TURKEY] = "Turkey"
        mCountryNameMap[Country.TRINIDAD_AND_TOBAGO] = "Trinidad And Tobago"
        mCountryNameMap[Country.TUVALU] = "Tuvalu"
        mCountryNameMap[Country.TAIWAN] = "Taiwan"
        mCountryNameMap[Country.TANZANIA] = "Tanzania"
        mCountryNameMap[Country.UKRAINE] = "Ukraine"
        mCountryNameMap[Country.UGANDA] = "Uganda"
        mCountryNameMap[Country.UNITED_KINGDOM] = "United Kingdom"
        mCountryNameMap[Country.UNITED_STATES] = "United States"
        mCountryNameMap[Country.URUGUAY] = "Uruguay"
        mCountryNameMap[Country.UZBEKISTAN] = "Uzbekistan"
        mCountryNameMap[Country.VENEZUELA] = "Venezuela"
        mCountryNameMap[Country.VIRGIN_ISLANDS_BRITISH] = "Virgin Islands (British)"
        mCountryNameMap[Country.VIETNAM] = "Vietnam"
        mCountryNameMap[Country.VANUATU] = "Vanuatu"
        mCountryNameMap[Country.WALLIS_AND_FUTUNA_ISLANDS] = "Wallis And Futuna Islands"
        mCountryNameMap[Country.SAMOA] = "Samoa"
        mCountryNameMap[Country.YEMEN] = "Yemen"
        mCountryNameMap[Country.MAYOTTE] = "Mayotte"
        mCountryNameMap[Country.SOUTH_AFRICA] = "South Africa"
        mCountryNameMap[Country.ZAMBIA] = "Zambia"
        mCountryNameMap[Country.ZIMBABWE] = "Zimbabwe"

        mCountryKeyMap["Andorra, Principality Of"] = Country.ANDORRA
        mCountryKeyMap["United Arab Emirates"] = Country.UNITED_ARAB_EMIRATES
        mCountryKeyMap["Afghanistan, Islamic State Of"] = Country.AFGHANISTAN
        mCountryKeyMap["Antigua And Barbuda"] = Country.ANTIGUA_AND_BARBUDA
        mCountryKeyMap["Anguilla"] = Country.ANGUILLA
        mCountryKeyMap["Albania"] = Country.ALBANIA
        mCountryKeyMap["Armenia"] = Country.ARMENIA
        mCountryKeyMap["Angola"] = Country.ANGOLA
        mCountryKeyMap["Argentina"] = Country.ARGENTINA
        mCountryKeyMap["American Samoa"] = Country.AMERICAN_SAMOA
        mCountryKeyMap["Austria"] = Country.AUSTRIA
        mCountryKeyMap["Australia"] = Country.AUSTRALIA
        mCountryKeyMap["Aruba"] = Country.ARUBA
        mCountryKeyMap["Azerbaidjan"] = Country.AZERBAIDJAN
        mCountryKeyMap["Bosnia-Herzegovina"] = Country.BOSNIA_HERZEGOVINA
        mCountryKeyMap["Barbados"] = Country.BARBADOS
        mCountryKeyMap["Bangladesh"] = Country.BANGLADESH
        mCountryKeyMap["Belgium"] = Country.BELGIUM
        mCountryKeyMap["Burkina Faso"] = Country.BURKINA_FASO
        mCountryKeyMap["Bulgaria"] = Country.BULGARIA
        mCountryKeyMap["Bahrain"] = Country.BAHRAIN
        mCountryKeyMap["Burundi"] = Country.BURUNDI
        mCountryKeyMap["Benin"] = Country.BENIN
        mCountryKeyMap["Bermuda"] = Country.BERMUDA
        mCountryKeyMap["Brunei Darussalam"] = Country.BRUNEI_DARUSSALAM
        mCountryKeyMap["Bolivia"] = Country.BOLIVIA
        mCountryKeyMap["Brazil"] = Country.BRAZIL
        mCountryKeyMap["Bahamas"] = Country.BAHAMAS
        mCountryKeyMap["Bhutan"] = Country.BHUTAN
        mCountryKeyMap["Botswana"] = Country.BOTSWANA
        mCountryKeyMap["Belarus"] = Country.BELARUS
        mCountryKeyMap["Belize"] = Country.BELIZE
        mCountryKeyMap["Canada"] = Country.CANADA
        mCountryKeyMap["Central African Republic"] = Country.CENTRAL_AFRICAN_REPUBLIC
        mCountryKeyMap["Congo, The Democratic Republic Of The"] = Country.CONGO_THE_DEMOCRATIC_REPUBLIC_OF_THE
        mCountryKeyMap["Congo"] = Country.CONGO
        mCountryKeyMap["Switzerland"] = Country.SWITZERLAND
        mCountryKeyMap["Ivory Coast (Cote D'Ivoire)"] = Country.IVORY_COAST
        mCountryKeyMap["Cook Islands"] = Country.COOK_ISLANDS
        mCountryKeyMap["Chile"] = Country.CHILE
        mCountryKeyMap["Cameroon"] = Country.CAMEROON
        mCountryKeyMap["China"] = Country.CHINA
        mCountryKeyMap["Colombia"] = Country.COLOMBIA
        mCountryKeyMap["Costa Rica"] = Country.COSTA_RICA
        mCountryKeyMap["Cuba"] = Country.CUBA
        mCountryKeyMap["Cape Verde"] = Country.CAPE_VERDE
        mCountryKeyMap["Christmas Island"] = Country.CHRISTMAS_ISLAND
        mCountryKeyMap["Cyprus"] = Country.CYPRUS
        mCountryKeyMap["Czech Republic"] = Country.CZECH_REPUBLIC
        mCountryKeyMap["Germany"] = Country.GERMANY
        mCountryKeyMap["Djibouti"] = Country.DJIBOUTI
        mCountryKeyMap["Denmark"] = Country.DENMARK
        mCountryKeyMap["Dominica"] = Country.DOMINICA
        mCountryKeyMap["Dominican Republic"] = Country.DOMINICAN_REPUBLIC
        mCountryKeyMap["Algeria"] = Country.ALGERIA
        mCountryKeyMap["Ecuador"] = Country.ECUADOR
        mCountryKeyMap["Estonia"] = Country.ESTONIA
        mCountryKeyMap["Egypt"] = Country.EGYPT
        mCountryKeyMap["Eritrea"] = Country.ERITREA
        mCountryKeyMap["Spain"] = Country.SPAIN
        mCountryKeyMap["Ethiopia"] = Country.ETHIOPIA
        mCountryKeyMap["Finland"] = Country.FINLAND
        mCountryKeyMap["Fiji"] = Country.FIJI
        mCountryKeyMap["Falkland Islands"] = Country.FALKLAND_ISLANDS
        mCountryKeyMap["Micronesia"] = Country.MICRONESIA
        mCountryKeyMap["Faroe Islands"] = Country.FAROE_ISLANDS
        mCountryKeyMap["France"] = Country.FRANCE
        mCountryKeyMap["Gabon"] = Country.GABON
        mCountryKeyMap["Grenada"] = Country.GRENADA
        mCountryKeyMap["Georgia"] = Country.GEORGIA
        mCountryKeyMap["French Guyana"] = Country.FRENCH_GUYANA
        mCountryKeyMap["Ghana"] = Country.GHANA
        mCountryKeyMap["Gibraltar"] = Country.GIBRALTAR
        mCountryKeyMap["Greenland"] = Country.GREENLAND
        mCountryKeyMap["Gambia"] = Country.GAMBIA
        mCountryKeyMap["Guinea"] = Country.GUINEA
        mCountryKeyMap["Guadeloupe (French)"] = Country.GUADELOUPE_FRENCH
        mCountryKeyMap["Equatorial Guinea"] = Country.EQUATORIAL_GUINEA
        mCountryKeyMap["Greece"] = Country.GREECE
        mCountryKeyMap["S. Georgia & S. Sandwich Isls."] = Country.S_GEORGIA_S_SANDWICH_ISLS
        mCountryKeyMap["Guatemala"] = Country.GUATEMALA
        mCountryKeyMap["Guam (USA)"] = Country.GUAM_USA
        mCountryKeyMap["Guinea Bissau"] = Country.GUINEA_BISSAU
        mCountryKeyMap["Guyana"] = Country.GUYANA
        mCountryKeyMap["Hong Kong"] = Country.HONG_KONG
        mCountryKeyMap["Honduras"] = Country.HONDURAS
        mCountryKeyMap["Croatia"] = Country.CROATIA
        mCountryKeyMap["Haiti"] = Country.HAITI
        mCountryKeyMap["Hungary"] = Country.HUNGARY
        mCountryKeyMap["Indonesia"] = Country.INDONESIA
        mCountryKeyMap["Ireland"] = Country.IRELAND
        mCountryKeyMap["Israel"] = Country.ISRAEL
        mCountryKeyMap["India"] = Country.INDIA
        mCountryKeyMap["British Indian Ocean Territory"] = Country.BRITISH_INDIAN_OCEAN_TERRITORY
        mCountryKeyMap["Iraq"] = Country.IRAQ
        mCountryKeyMap["Iran"] = Country.IRAN
        mCountryKeyMap["Iceland"] = Country.ICELAND
        mCountryKeyMap["Italy"] = Country.ITALY
        mCountryKeyMap["Jamaica"] = Country.JAMAICA
        mCountryKeyMap["Jordan"] = Country.JORDAN
        mCountryKeyMap["Japan"] = Country.JAPAN
        mCountryKeyMap["Kenya"] = Country.KENYA
        mCountryKeyMap["Kyrgyz Republic (Kyrgyzstan)"] = Country.KYRGYZ_REPUBLIC_KYRGYZSTAN
        mCountryKeyMap["Cambodia, Kingdom Of"] = Country.CAMBODIA_KINGDOM_OF
        mCountryKeyMap["Kiribati"] = Country.KIRIBATI
        mCountryKeyMap["Comoros"] = Country.COMOROS
        mCountryKeyMap["Saint Kitts & Nevis Anguilla"] = Country.SAINT_KITTS_NEVIS_ANGUILLA
        mCountryKeyMap["North Korea"] = Country.NORTH_KOREA
        mCountryKeyMap["South Korea"] = Country.SOUTH_KOREA
        mCountryKeyMap["Kuwait"] = Country.KUWAIT
        mCountryKeyMap["Cayman Islands"] = Country.CAYMAN_ISLANDS
        mCountryKeyMap["Kazakhstan"] = Country.KAZAKHSTAN
        mCountryKeyMap["Laos"] = Country.LAOS
        mCountryKeyMap["Lebanon"] = Country.LEBANON
        mCountryKeyMap["Liechtenstein"] = Country.LIECHTENSTEIN
        mCountryKeyMap["Srilanka"] = Country.SRILANKA
        mCountryKeyMap["Liberia"] = Country.LIBERIA
        mCountryKeyMap["Lesotho"] = Country.LESOTHO
        mCountryKeyMap["Lithuania"] = Country.LITHUANIA
        mCountryKeyMap["Luxembourg"] = Country.LUXEMBOURG
        mCountryKeyMap["Latvia"] = Country.LATVIA
        mCountryKeyMap["Libya"] = Country.LIBYA
        mCountryKeyMap["Morocco"] = Country.MOROCCO
        mCountryKeyMap["Monaco"] = Country.MONACO
        mCountryKeyMap["Moldavia"] = Country.MOLDAVIA
        mCountryKeyMap["Madagascar"] = Country.MADAGASCAR
        mCountryKeyMap["Marshall Islands"] = Country.MARSHALL_ISLANDS
        mCountryKeyMap["Macedonia"] = Country.MACEDONIA
        mCountryKeyMap["Mali"] = Country.MALI
        mCountryKeyMap["Myanmar"] = Country.MYANMAR
        mCountryKeyMap["Mongolia"] = Country.MONGOLIA
        mCountryKeyMap["Macau"] = Country.MACAU
        mCountryKeyMap["Northern Mariana Islands"] = Country.NORTHERN_MARIANA_ISLANDS
        mCountryKeyMap["Martinique (French)"] = Country.MARTINIQUE_FRENCH
        mCountryKeyMap["Mauritania"] = Country.MAURITANIA
        mCountryKeyMap["Montserrat"] = Country.MONTSERRAT
        mCountryKeyMap["Malta"] = Country.MALTA
        mCountryKeyMap["Mauritius"] = Country.MAURITIUS
        mCountryKeyMap["Maldives"] = Country.MALDIVES
        mCountryKeyMap["Malawi"] = Country.MALAWI
        mCountryKeyMap["Mexico"] = Country.MEXICO
        mCountryKeyMap["Malaysia"] = Country.MALAYSIA
        mCountryKeyMap["Namibia"] = Country.NAMIBIA
        mCountryKeyMap["New Caledonia (French)"] = Country.NEW_CALEDONIA_FRENCH
        mCountryKeyMap["Niger"] = Country.NIGER
        mCountryKeyMap["Norfolk Island"] = Country.NORFOLK_ISLAND
        mCountryKeyMap["Nigeria"] = Country.NIGERIA
        mCountryKeyMap["Nicaragua"] = Country.NICARAGUA
        mCountryKeyMap["Netherlands"] = Country.NETHERLANDS
        mCountryKeyMap["Norway"] = Country.NORWAY
        mCountryKeyMap["Nepal"] = Country.NEPAL
        mCountryKeyMap["Nauru"] = Country.NAURU
        mCountryKeyMap["Niue"] = Country.NIUE
        mCountryKeyMap["New Zealand"] = Country.NEW_ZEALAND
        mCountryKeyMap["Oman"] = Country.OMAN
        mCountryKeyMap["Panama"] = Country.PANAMA
        mCountryKeyMap["Peru"] = Country.PERU
        mCountryKeyMap["Polynesia (French)"] = Country.POLYNESIA_FRENCH
        mCountryKeyMap["Papua New Guinea"] = Country.PAPUA_NEW_GUINEA
        mCountryKeyMap["Philippines"] = Country.PHILIPPINES
        mCountryKeyMap["Pakistan"] = Country.PAKISTAN
        mCountryKeyMap["Poland"] = Country.POLAND
        mCountryKeyMap["Puerto Rico"] = Country.PUERTO_RICO
        mCountryKeyMap["Portugal"] = Country.PORTUGAL
        mCountryKeyMap["Palau"] = Country.PALAU
        mCountryKeyMap["Paraguay"] = Country.PARAGUAY
        mCountryKeyMap["Qatar"] = Country.QATAR
        mCountryKeyMap["Reunion (French)"] = Country.REUNION_FRENCH
        mCountryKeyMap["Romania"] = Country.ROMANIA
        mCountryKeyMap["Russian Federation"] = Country.RUSSIAN_FEDERATION
        mCountryKeyMap["Rwanda"] = Country.RWANDA
        mCountryKeyMap["Saudi Arabia"] = Country.SAUDI_ARABIA
        mCountryKeyMap["Solomon Islands"] = Country.SOLOMON_ISLANDS
        mCountryKeyMap["Seychelles"] = Country.SEYCHELLES
        mCountryKeyMap["Sudan"] = Country.SUDAN
        mCountryKeyMap["Sweden"] = Country.SWEDEN
        mCountryKeyMap["Singapore"] = Country.SINGAPORE
        mCountryKeyMap["Slovenia"] = Country.SLOVENIA
        mCountryKeyMap["Slovak Republic"] = Country.SLOVAK_REPUBLIC
        mCountryKeyMap["Sierra Leone"] = Country.SIERRA_LEONE
        mCountryKeyMap["San Marino"] = Country.SAN_MARINO
        mCountryKeyMap["Senegal"] = Country.SENEGAL
        mCountryKeyMap["Somalia"] = Country.SOMALIA
        mCountryKeyMap["Suriname"] = Country.SURINAME
        mCountryKeyMap["El Salvador"] = Country.EL_SALVADOR
        mCountryKeyMap["Syria"] = Country.SYRIA
        mCountryKeyMap["Swaziland"] = Country.SWAZILAND
        mCountryKeyMap["Turks And Caicos Islands"] = Country.TURKS_AND_CAICOS_ISLANDS
        mCountryKeyMap["Chad"] = Country.CHAD
        mCountryKeyMap["Togo"] = Country.TOGO
        mCountryKeyMap["Thailand"] = Country.THAILAND
        mCountryKeyMap["Tadjikistan"] = Country.TADJIKISTAN
        mCountryKeyMap["Tokelau"] = Country.TOKELAU
        mCountryKeyMap["Turkmenistan"] = Country.TURKMENISTAN
        mCountryKeyMap["Tunisia"] = Country.TUNISIA
        mCountryKeyMap["Tonga"] = Country.TONGA
        mCountryKeyMap["Turkey"] = Country.TURKEY
        mCountryKeyMap["Trinidad And Tobago"] = Country.TRINIDAD_AND_TOBAGO
        mCountryKeyMap["Tuvalu"] = Country.TUVALU
        mCountryKeyMap["Taiwan"] = Country.TAIWAN
        mCountryKeyMap["Tanzania"] = Country.TANZANIA
        mCountryKeyMap["Ukraine"] = Country.UKRAINE
        mCountryKeyMap["Uganda"] = Country.UGANDA
        mCountryKeyMap["United Kingdom"] = Country.UNITED_KINGDOM
        mCountryKeyMap["United States"] = Country.UNITED_STATES
        mCountryKeyMap["Uruguay"] = Country.URUGUAY
        mCountryKeyMap["Uzbekistan"] = Country.UZBEKISTAN
        mCountryKeyMap["Venezuela"] = Country.VENEZUELA
        mCountryKeyMap["Virgin Islands (British)"] = Country.VIRGIN_ISLANDS_BRITISH
        mCountryKeyMap["Vietnam"] = Country.VIETNAM
        mCountryKeyMap["Vanuatu"] = Country.VANUATU
        mCountryKeyMap["Wallis And Futuna Islands"] = Country.WALLIS_AND_FUTUNA_ISLANDS
        mCountryKeyMap["Samoa"] = Country.SAMOA
        mCountryKeyMap["Yemen"] = Country.YEMEN
        mCountryKeyMap["Mayotte"] = Country.MAYOTTE
        mCountryKeyMap["South Africa"] = Country.SOUTH_AFRICA
        mCountryKeyMap["Zambia"] = Country.ZAMBIA
        mCountryKeyMap["Zimbabwe"] = Country.ZIMBABWE
    }

    /**
     * This method provides the country names
     *
     * @return [List] country names
     * */
    fun getCountryNames(): List<String> {
        return ArrayList(mCountryNameMap.values)
    }

    /**
     * This method provides the country codes
     *
     * @return [List] country codes
     * */
    fun getCountryCodes(): List<String> {
        return ArrayList(mCountryCodeMap.values)
    }

    /**
     * This method provides the country keys
     *
     * @return [List] country keys
     * */
    fun getCountryKeys(): List<String> {
        return ArrayList(mCountryKeyMap.values)
    }

    /**
     * This method provides the country code by country name
     *
     * @param countryName country name
     * @return [String] country code
     * */
    fun getCountryCodeByName(countryName: String): String {
        return getCountryCodeByKey(getCountryKeyByName(countryName))
    }

    /**
     * This method provides the country code by country key
     *
     * @param countryKey country key
     * @return [String] country code
     * */
    fun getCountryCodeByKey(countryKey: String): String {
        var countryCode: String = Constants.Default.DEFAULT_STRING

        if (mCountryCodeMap.containsKey(countryKey)) {
            countryCode = mCountryCodeMap[countryKey]!!
        }

        return countryCode
    }

    /**
     * This method provides the country key by country code
     *
     * @param countryCode country code
     * @return [String] country key
     * */
    fun getCountryKeyByCode(countryCode: String): String {
        var countryKey = Constants.Default.DEFAULT_STRING

        if (mCountryCodeMap.containsValue(countryCode)) {
            for ((key, value) in mCountryCodeMap) {
                if (value == countryCode) {
                    countryKey = key
                    break
                }
            }
        }

        return countryKey
    }

    /**
     * This method provides the country key by country name
     *
     * @param countryName country name
     * @return [String] country key
     * */
    fun getCountryKeyByName(countryName: String): String {
        var countryKey: String = Constants.Default.DEFAULT_STRING

        if (mCountryKeyMap.containsKey(countryName)) {
            countryKey = mCountryKeyMap[countryName]!!
        }

        return countryKey
    }

    /**
     * This method provides the country name by country key
     *
     * @param countryKey country key
     * @return [String] country name
     * */
    fun getCountryNameByKey(countryKey: String): String {
        var countryName: String = Constants.Default.DEFAULT_STRING

        if (mCountryNameMap.containsKey(countryKey)) {
            countryName = mCountryNameMap[countryKey]!!
        }

        return countryName
    }

    /**
     * This method provides the country name by country code
     *
     * @param countryCode country code
     * @return [String] country name
     * */
    fun getCountryNameByCode(countryCode: String): String {
        return getCountryNameByKey(getCountryKeyByCode(countryCode))
    }

    class Country {
        companion object {
            const val ANDORRA = "AD"
            const val UNITED_ARAB_EMIRATES = "AE"
            const val AFGHANISTAN = "AF"
            const val ANTIGUA_AND_BARBUDA = "AG"
            const val ANGUILLA = "AI"
            const val ALBANIA = "AL"
            const val ARMENIA = "AM"
            const val ANGOLA = "AO"
            const val ARGENTINA = "AR"
            const val AMERICAN_SAMOA = "AS"
            const val AUSTRIA = "AT"
            const val AUSTRALIA = "AU"
            const val ARUBA = "AW"
            const val AZERBAIDJAN = "AZ"
            const val BOSNIA_HERZEGOVINA = "BA"
            const val BARBADOS = "BB"
            const val BANGLADESH = "BD"
            const val BELGIUM = "BE"
            const val BURKINA_FASO = "BF"
            const val BULGARIA = "BG"
            const val BAHRAIN = "BH"
            const val BURUNDI = "BI"
            const val BENIN = "BJ"
            const val BERMUDA = "BM"
            const val BRUNEI_DARUSSALAM = "BN"
            const val BOLIVIA = "BO"
            const val BRAZIL = "BR"
            const val BAHAMAS = "BS"
            const val BHUTAN = "BT"
            const val BOTSWANA = "BW"
            const val BELARUS = "BY"
            const val BELIZE = "BZ"
            const val CANADA = "CA"
            const val CENTRAL_AFRICAN_REPUBLIC = "CF"
            const val CONGO_THE_DEMOCRATIC_REPUBLIC_OF_THE = "CD"
            const val CONGO = "CG"
            const val SWITZERLAND = "CH"
            const val IVORY_COAST = "CI"
            const val COOK_ISLANDS = "CK"
            const val CHILE = "CL"
            const val CAMEROON = "CM"
            const val CHINA = "CN"
            const val COLOMBIA = "CO"
            const val COSTA_RICA = "CR"
            const val CUBA = "CU"
            const val CAPE_VERDE = "CV"
            const val CHRISTMAS_ISLAND = "CX"
            const val CYPRUS = "CY"
            const val CZECH_REPUBLIC = "CZ"
            const val GERMANY = "DE"
            const val DJIBOUTI = "DJ"
            const val DENMARK = "DK"
            const val DOMINICA = "DM"
            const val DOMINICAN_REPUBLIC = "DO"
            const val ALGERIA = "DZ"
            const val ECUADOR = "EC"
            const val ESTONIA = "EE"
            const val EGYPT = "EG"
            const val ERITREA = "ER"
            const val SPAIN = "ES"
            const val ETHIOPIA = "ET"
            const val FINLAND = "FI"
            const val FIJI = "FJ"
            const val FALKLAND_ISLANDS = "FK"
            const val MICRONESIA = "FM"
            const val FAROE_ISLANDS = "FO"
            const val FRANCE = "FR"
            const val GABON = "GA"
            const val GRENADA = "GD"
            const val GEORGIA = "GE"
            const val FRENCH_GUYANA = "GF"
            const val GHANA = "GH"
            const val GIBRALTAR = "GI"
            const val GREENLAND = "GL"
            const val GAMBIA = "GM"
            const val GUINEA = "GN"
            const val GUADELOUPE_FRENCH = "GP"
            const val EQUATORIAL_GUINEA = "GQ"
            const val GREECE = "GR"
            const val S_GEORGIA_S_SANDWICH_ISLS = "GS"
            const val GUATEMALA = "GT"
            const val GUAM_USA = "GU"
            const val GUINEA_BISSAU = "GW"
            const val GUYANA = "GY"
            const val HONG_KONG = "HK"
            const val HONDURAS = "HN"
            const val CROATIA = "HR"
            const val HAITI = "HT"
            const val HUNGARY = "HU"
            const val INDONESIA = "ID"
            const val IRELAND = "IE"
            const val ISRAEL = "IL"
            const val INDIA = "IN"
            const val BRITISH_INDIAN_OCEAN_TERRITORY = "IO"
            const val IRAQ = "IQ"
            const val IRAN = "IR"
            const val ICELAND = "IS"
            const val ITALY = "IT"
            const val JAMAICA = "JM"
            const val JORDAN = "JO"
            const val JAPAN = "JP"
            const val KENYA = "KE"
            const val KYRGYZ_REPUBLIC_KYRGYZSTAN = "KG"
            const val CAMBODIA_KINGDOM_OF = "KH"
            const val KIRIBATI = "KI"
            const val COMOROS = "KM"
            const val SAINT_KITTS_NEVIS_ANGUILLA = "KN"
            const val NORTH_KOREA = "KP"
            const val SOUTH_KOREA = "KR"
            const val KUWAIT = "KW"
            const val CAYMAN_ISLANDS = "KY"
            const val KAZAKHSTAN = "KZ"
            const val LAOS = "LA"
            const val LEBANON = "LB"
            const val LIECHTENSTEIN = "LI"
            const val SRILANKA = "LK"
            const val LIBERIA = "LR"
            const val LESOTHO = "LS"
            const val LITHUANIA = "LT"
            const val LUXEMBOURG = "LU"
            const val LATVIA = "LV"
            const val LIBYA = "LY"
            const val MOROCCO = "MA"
            const val MONACO = "MC"
            const val MOLDAVIA = "MD"
            const val MADAGASCAR = "MG"
            const val MARSHALL_ISLANDS = "MH"
            const val MACEDONIA = "MK"
            const val MALI = "ML"
            const val MYANMAR = "MM"
            const val MONGOLIA = "MN"
            const val MACAU = "MO"
            const val NORTHERN_MARIANA_ISLANDS = "MP"
            const val MARTINIQUE_FRENCH = "MQ"
            const val MAURITANIA = "MR"
            const val MONTSERRAT = "MS"
            const val MALTA = "MT"
            const val MAURITIUS = "MU"
            const val MALDIVES = "MV"
            const val MALAWI = "MW"
            const val MEXICO = "MX"
            const val MALAYSIA = "MY"
            const val NAMIBIA = "NA"
            const val NEW_CALEDONIA_FRENCH = "NC"
            const val NIGER = "NE"
            const val NORFOLK_ISLAND = "NF"
            const val NIGERIA = "NG"
            const val NICARAGUA = "NI"
            const val NETHERLANDS = "NL"
            const val NORWAY = "NO"
            const val NEPAL = "NP"
            const val NAURU = "NR"
            const val NIUE = "NU"
            const val NEW_ZEALAND = "NZ"
            const val OMAN = "OM"
            const val PANAMA = "PA"
            const val PERU = "PE"
            const val POLYNESIA_FRENCH = "PF"
            const val PAPUA_NEW_GUINEA = "PG"
            const val PHILIPPINES = "PH"
            const val PAKISTAN = "PK"
            const val POLAND = "PL"
            const val PUERTO_RICO = "PR"
            const val PORTUGAL = "PT"
            const val PALAU = "PW"
            const val PARAGUAY = "PY"
            const val QATAR = "QA"
            const val REUNION_FRENCH = "RE"
            const val ROMANIA = "RO"
            const val RUSSIAN_FEDERATION = "RU"
            const val RWANDA = "RW"
            const val SAUDI_ARABIA = "SA"
            const val SOLOMON_ISLANDS = "SB"
            const val SEYCHELLES = "SC"
            const val SUDAN = "SD"
            const val SWEDEN = "SE"
            const val SINGAPORE = "SG"
            const val SLOVENIA = "SI"
            const val SLOVAK_REPUBLIC = "SK"
            const val SIERRA_LEONE = "SL"
            const val SAN_MARINO = "SM"
            const val SENEGAL = "SN"
            const val SOMALIA = "SO"
            const val SURINAME = "SR"
            const val EL_SALVADOR = "SV"
            const val SYRIA = "SY"
            const val SWAZILAND = "SZ"
            const val TURKS_AND_CAICOS_ISLANDS = "TC"
            const val CHAD = "TD"
            const val TOGO = "TG"
            const val THAILAND = "TH"
            const val TADJIKISTAN = "TJ"
            const val TOKELAU = "TK"
            const val TURKMENISTAN = "TM"
            const val TUNISIA = "TN"
            const val TONGA = "TO"
            const val TURKEY = "TR"
            const val TRINIDAD_AND_TOBAGO = "TT"
            const val TUVALU = "TV"
            const val TAIWAN = "TW"
            const val TANZANIA = "TZ"
            const val UKRAINE = "UA"
            const val UGANDA = "UG"
            const val UNITED_KINGDOM = "UK"
            const val UNITED_STATES = "US"
            const val URUGUAY = "UY"
            const val UZBEKISTAN = "UZ"
            const val VENEZUELA = "VE"
            const val VIRGIN_ISLANDS_BRITISH = "VG"
            const val VIETNAM = "VN"
            const val VANUATU = "VU"
            const val WALLIS_AND_FUTUNA_ISLANDS = "WF"
            const val SAMOA = "WS"
            const val YEMEN = "YE"
            const val MAYOTTE = "YT"
            const val SOUTH_AFRICA = "ZA"
            const val ZAMBIA = "ZM"
            const val ZIMBABWE = "ZW"
        }
    }
}
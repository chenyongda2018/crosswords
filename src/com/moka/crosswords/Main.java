package com.moka.crosswords;

import com.moka.crosswords.util.Orientation;

public class Main {

    public static void main(String[] args) {
        new Crosswords(
            /* List of words to add */
            new String[]{ "shirt","harting","dear","harmful","shovel","dislike","plausibly","spade","verbally","rancidity","chimneys","knapsack","flip","lying","excitement","plastic","untagged","unroll","spool","stingily","mainsail","endoderm","fallopian","recharger","taking","dixie","stuffing","evade","atom","houxty","crashing","superuser","terms","tight","duke","chortle","knag","zirconium","anchor","locate","shucking","forelock","merciful","frazzled","fabbing","giggly","groon","bonnet","cluck","chelsea","spacket","seasoned","burp","search","welloff","fruitful","tumley","fabric","hard","harbin","hope","shortcut","mones","caution","quarter","rein","brent","gemini","cocklemore","unelected","presto","strain","pinned","cohesive","unfilled","lochsa","curvy","bootless","dacket","samoan","chops","unwilling","soups","velvet","tangle","winner","fifth","otter","pegasus","diameter","onward","thank","shenanigan","recite","wolf","elope","jiffy","trevor","normal","visual","catena","atop","lagoon","macaw","cider","rigid","fees","antacid","duller","homes","etiquette","come","emulsion","scenario","snaking","gleeful","jaywalker","crew","ivan","smith","roy","slips","scraped","shirley","chav","silly","licking","fashions","trysail","broom","cumbrian","visits","hippers","oakum","providence","nicken","indolent","primarily","quadrat","bovine","wanny","denby","concerns","wooler","procyon","highlight","knapsack","tasting","curves","quickest","dextrous","varenne","sour","wackets","conceded","glowworm","gordon","eyepiece","earby","dyke","hawser","cur","gathering","platonic","evidence","wine","repeat","showing","wander","watery","tummy","pick","unbounded","peb","kabob","dry","smartie","daring","fungus","bookmark","pleet","print","polymer","pacifism","bella","vanilla","immaculate","californium","macaron","sedate","multimeter","clarify","plymouth","silly","telrad","witted","trapped","alatna","handled","leeks","overvalue","chip","waiting","salami","physical","anxiety","americium","kame","avon","bacon","spotify","changed","absinthe","bagel","greet","varuna","soloman","exacting","affecting","lily","unseated","bulb","tuck","sandworm","strategy","monitor","aware","negate","dejected","fig","alfalfa","wish","sammy","cornwall","scans","climb","canoeing","tethys","eaten","spread","beaver","easily","pantofle","maisey","ebook","cetus","zia","wastebasket","untrained","whimper"},
            /* List of allowed orientations for words */
            new int[]{Orientation.HORI_RIGHT, Orientation.VERT_DOWN}
        );
    }
}

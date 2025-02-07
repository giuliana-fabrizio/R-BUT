import { Character, Town, Street, Shop, Item} from './model'

// helpers method for shops
function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

function fillShopStock(shop) {
  // fill tab with items matching the store categories
  let tab = items.filter(e => shop.itemCat.includes(e.type))
  // pick 10 at random, may lead to several occurences of the same item
  for(let i=0;i<10;i++) {
    let idx = getRandomInt(tab.length)
    shop.itemStock.push(tab[idx])
  }
}

function fillShopOrder(shop) {
  /* fill tab with items matching the store cat + not in itemStock
     NB: this is a new version, much more compact than that provided in TP 2 solution.
     Nevertheless, it is less readable since we use a function call within a composition of
     conditions.
   */
  let tab = items.filter(e => ((shop.itemCat.includes(e.type)) && (shop.itemStock.find(i => i.name === e.name) === undefined) ) )
  if (tab.length === 0) return
  // get 5 different items at random => need to remove chosen in tab
  for(let i=0;i<5;i++) {
    let idx = getRandomInt(tab.length)
    shop.itemOrder.push(tab[idx])
    tab.splice(idx,1)
    // stop choosing if there are no more items
    if (tab.length === 0) break;
  }
}

// create items
var items = [
  new Item('conic helmet', 'helmet', 200, 'A+10'),
  new Item('great crown of apologia', 'crown', 200, 'A+20'),
  new Item('band of joy', 'crown', 100, 'L+10'),
  new Item('leather protection', 'armor', 100, 'A+10'),
  new Item('broigne', 'armor', 200, 'A+20'),
  new Item('hauberk', 'armor', 500, 'A+40'),
  new Item('plate protection', 'armor', 1000, 'A+60'),
  new Item('tuxedo', 'clothes', 600, 'L+1'),
  new Item('cursed swimsuit', 'clothes', 10, 'A-10'),
  new Item('unicorn cosplay', 'clothes', 200, 'L+10'),
  new Item('dagger', 'weapon', 100, 'S+5'),
  new Item('cursed dagger', 'weapon', 100, 'S-5'),
  new Item('short sword', 'weapon', 200, 'S+10'),
  new Item('cursed short sword', 'weapon', 200, 'S-10'),
  new Item('long sword', 'weapon', 300, 'S+20'),
  new Item('cursed long sword', 'weapon', 300, 'S-20'),
  new Item('axe', 'weapon', 100, 'S+10'),
  new Item('cursed axe', 'weapon', 100, 'S-10'),
  new Item('great axe', 'weapon', 200, 'S+20'),
  new Item('cursed great axe', 'weapon', 200, 'S-20'),
  new Item('torch', 'lighter', 2, ''),
  new Item('oil lamp', 'lighter', 10, ''),
  new Item('leather purse', 'purse', 10, ''),
  new Item('protection potion', 'potion', 100, 'a+10'),
  new Item('health potion', 'potion', 100, 'l+10'),
  new Item('force potion', 'potion', 100, 's+10'),
  new Item('fireball', 'spell', 1000, ''),
  new Item('ice cone', 'spell', 1000, ''),
  new Item('total healing', 'spell', 1000, ''),
  new Item('invisibility', 'spell', 1000, ''),
  new Item('levitation', 'spell', 1000, ''),
  new Item('apple', 'food', 1, 'l+1'),
  new Item('chicken', 'food', 10, 'l+5'),
  new Item('beef', 'food', 15, 'l+10'),
  new Item('wine', 'food', 2, 'l+2')
];


// **************************
// create town1
// **************************

// cat for shop11, shop12 shop13
let cat11 = [ 'helmet', 'armor', 'weapon' ];
// cat for shop14, shop15 shop16
let cat12 = [ 'clothes', 'purse', 'food', 'lighter' ];
// cat for shop17, shop18 shop19
let cat13 = [ 'crown', 'spell', 'potion' ];

let shop11 = new Shop("Patty Smith",cat11);
let shop12 = new Shop("I smith you",cat11);
let shop13 = new Shop("Dingue de marteau",cat11);
fillShopStock(shop11);
fillShopOrder(shop11);
fillShopStock(shop12);
fillShopOrder(shop12);
fillShopStock(shop13);
fillShopOrder(shop13);
let street11 = new Street("Smith Street");
street11.addShop(shop11);
street11.addShop(shop12);
street11.addShop(shop13);

let shop14 = new Shop("My tailor is rich",cat12);
let shop15 = new Shop("La grosse Erie",cat12);
let shop16 = new Shop("Les Zoeurs",cat12);
fillShopStock(shop14);
fillShopOrder(shop14);
fillShopStock(shop15);
fillShopOrder(shop15);
fillShopStock(shop16);
fillShopOrder(shop16);
let street12 = new Street("Quality Street");
street12.addShop(shop14);
street12.addShop(shop15);
street12.addShop(shop16);

let shop17 = new Shop("Wizzz",cat13);
let shop18 = new Shop("A Bric-a-brac dabra",cat13);
let shop19 = new Shop("La bonne baguette",cat13);
fillShopStock(shop17);
fillShopOrder(shop17);
fillShopStock(shop18);
fillShopOrder(shop18);
fillShopStock(shop19);
fillShopOrder(shop19);
let street13 = new Street("Magic Street");
street13.addShop(shop17);
street13.addShop(shop18);
street13.addShop(shop19);

let town1 = new Town("Metropolis");
town1.addStreet(street11);
town1.addStreet(street12);
town1.addStreet(street13);


// create town2
// cat for shop21, shop22
let cat21 = [ 'crown', 'clothes' ];
// cat for shop24, shop25 shop26
let cat22 = [ 'purse', 'food', 'lighter' ];
// cat for shop27, shop28
let cat23 = [ 'spell' ];

let shop21 = new Shop("Crown her",cat21);
let shop22 = new Shop("Dressez-les",cat21);
fillShopStock(shop21);
fillShopOrder(shop21);
fillShopStock(shop22);
fillShopOrder(shop22);
let street21 = new Street("Tailor Street");
street21.addShop(shop21);
street21.addShop(shop22);

let shop24 = new Shop("100 patates",cat22);
let shop25 = new Shop("Ah la bonne carotte",cat22);
let shop26 = new Shop("Le choux fleur en nez",cat22);
fillShopStock(shop24);
fillShopOrder(shop24);
fillShopStock(shop25);
fillShopOrder(shop25);
fillShopStock(shop26);
fillShopOrder(shop26);
let street22 = new Street("Grosse Ery Street");
street22.addShop(shop24);
street22.addShop(shop25);
street22.addShop(shop26);

let shop27 = new Shop("C'est pas sorcier",cat23);
let shop28 = new Shop("I put a spell on you",cat23);
fillShopStock(shop27);
fillShopOrder(shop27);
fillShopStock(shop28);
fillShopOrder(shop28);
let street23 = new Street("Poudlard Street");
street23.addShop(shop27);
street23.addShop(shop28);

let town2 = new Town("Atropos");
town2.addStreet(street21);
town2.addStreet(street22);
town2.addStreet(street23);

// create town3
let cat31 = [ 'spell', 'potion' ];
let cat32 = [ 'clothes', 'purse' ];
let cat33 = [ 'food', 'lighter' ];
let cat34 = [ 'helmet', 'armor', 'weapon', 'crown' ];

let shop31 = new Shop("Khant spell",cat31);
let shop32 = new Shop("Beurk",cat31);
fillShopStock(shop31);
fillShopOrder(shop31);
fillShopStock(shop32);
fillShopOrder(shop32);

let street31 = new Street("Voodoo Street");
street31.addShop(shop31);
street31.addShop(shop32);

let shop33 = new Shop("Ricci",cat32);
let shop34 = new Shop("Channel",cat32);
let shop35 = new Shop("Saint Laurent",cat32);
let shop36 = new Shop("Gaultier",cat32);
fillShopStock(shop33);
fillShopOrder(shop33);
fillShopStock(shop34);
fillShopOrder(shop34);
fillShopStock(shop35);
fillShopOrder(shop35);
fillShopStock(shop36);
fillShopOrder(shop36);
let street32 = new Street("Lux Street");
street32.addShop(shop33);
street32.addShop(shop34);
street32.addShop(shop35);
street32.addShop(shop36);

let shop37 = new Shop("C'est du donner",cat33);
fillShopStock(shop37);
fillShopOrder(shop37);
let street33 = new Street("Kebab street");
street33.addShop(shop37);

let shop38 = new Shop("Epais an vrak",cat34);
let shop39 = new Shop("Le bout qui l'y est",cat34);
let shop310 = new Shop("la coup ronne",cat34);
fillShopStock(shop38);
fillShopOrder(shop38);
fillShopStock(shop39);
fillShopOrder(shop39);
fillShopStock(shop310);
fillShopOrder(shop310);
let street34 = new Street("Larrue sent non");
street34.addShop(shop38);
street34.addShop(shop39);
street34.addShop(shop310);

let town3 = new Town("Babylonia");
town3.addStreet(street31);
town3.addStreet(street32);
town3.addStreet(street33);
town3.addStreet(street34);

var towns = [
  town1, town2, town3
]

// create characs
var characs = [
  new Character("Conan",1), new Character("Xena",2)
];
// assign items to conan
characs[0].slots[0].items.push(items[0]);
characs[0].slots[1].items.push(items[3]);
characs[0].slots[2].items.push(items[12]);
characs[0].slots[2].items.push(items[16]);
characs[0].boughtItems.push(items[26]);
characs[0].boughtItems.push(items[33]);

export { items, towns, characs}
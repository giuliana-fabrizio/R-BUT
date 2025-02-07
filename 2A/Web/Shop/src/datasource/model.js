var itemCats = [ 'helmet', 'crown', 'armor', 'clothes', 'weapon', 'lighter', 'purse', 'potion', 'spell', 'food'];

class Item {
  static counter = 0;
  constructor(name, type, price, effect) {
    Item.counter++;
    this._id = Item.counter
    this.name = name
    // test si le type donné en param est valide
    if(itemCats.includes(type)) {
      this.type = type
    }
    else {
      this.type = ''
    }
    if (price>=0) {
      this.price = price
    }
    else {
      this.price = 0
    }
    this.effect = effect
  }
}

class Shop {
  static counter = 0;
  constructor(name, itemCat) {
    Shop.counter++;
    this._id = Shop.counter;
    this.name = name;
    this.itemCat = [...itemCat];
    this.itemStock = [];
    this.itemOrder = [];
  }
}

class Street {
  static counter = 0;
  constructor(name) {
    Street.counter++;
    this._id = Street.counter;
    this.name = name;
    this.shops = [];
  }

  addShop(shop) {
    this.shops.push(shop);
  }
}

class Town {
  static counter = 0;
  constructor(name) {
    Town.counter++;
    this._id = Town.counter;
    this.name = name;
    this.streets = [];
  }

  addStreet(street) {
    this.streets.push(street);
  }

}

class Slot {

  constructor(_id, name) {
    this._id = _id
    this.name = name
    this.items = []
  }
}

class Character {

  static counter = 0;
  constructor(name, level) {
    Character.counter++;
    this._id = Character.counter;
    this.name = name;
    this.level = level;
    this.slots = [];
    this.slots.push(new Slot(1,'head'))
    this.slots.push(new Slot(2,'body'))
    this.slots.push(new Slot(3,'hands'))
    this.slots.push(new Slot(4,'belt'))
    this.slots.push(new Slot(5,'bag'))
    this.boughtItems = []; // list of item bought but not yet assigned

    this.vitality = this.level*50
    this.life = this.vitality
    this.strength = this.level*20
    this.armor = 0
    this.gold = 450
  }
}

export { Slot, Character, Town, Street, Shop, Item, itemCats}
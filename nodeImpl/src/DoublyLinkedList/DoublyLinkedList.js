import Node from './Node.js';

class DoublyLinkedList{
  constructor(){
    this.head = null;
    this.tail = null;
  }

  getHead()
  {
    return this.head;
  }

  getTail()
  {
    return this.tail;
  }

  add(data){
    const node = new Node(data, null, null);
   if(this.head === null){
     this.head = node;
     this.tail = node;
     return this.head;
   } else {
     this.tail.next = node;
     node.prev = this.tail;
     node.next = null;
     this.tail = node;
   }
   return node;
}

remove(node){
  const nextNode = node.next;
  const prevNode = node.prev;
  if(node === this.head){
    this.head = this.head.next
    node.next = null;
    node.prev = null;
    return this.head;
  }
  if(node === this.tail){
    const newTail = this.tail.prev;
    this.tail.prev = null;
    this.tail = newTail;
    node.next = null;
    node.prev = null;
    return this.head;
  }
  prevNode.next = nextNode;
  nextNode.prev = prevNode;
  node.next = null;
  node.prev = null;
  return node;
}

getSize(){
  let size = 0;
  let temp = this.head;
  while(temp !== null){
    size = size + 1;
    temp = temp.next;
  }
  return size;
}

display(){
  let temp = this.head;
  let string = "";
  while(temp !== null){
    string = string + " " + temp.data;
    temp =  temp.next;
  }
  console.log(string);
  return string;
}

}

export default DoublyLinkedList;

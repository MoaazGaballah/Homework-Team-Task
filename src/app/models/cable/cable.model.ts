export class Cable {

  public cableName: string;
  public cableType : string;
  public length : number;
  public price : number;
  public thickness : number;

  constructor(cableName: string, cableType : string, length : number, price : number, thickness : number) {
    this.cableName = cableName;
    this.cableType = cableType;
    this.length = length;
    this.price = price;
    this.thickness = thickness;
  }
}

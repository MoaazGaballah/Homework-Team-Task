import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {Cable} from "../models/cable/cable.model";
import {HttpService} from "./common/http.service";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable()
export class CableService {

  private baseURL = 'http://localhost:10010/cable';

  constructor(private httpService: HttpService) {}

  getCables(query: string, size: number, page: number, sortParam: string, sortDirection: string) {
    let getEndpoint = '/get-all';
    return this.httpService.get(this.baseURL + getEndpoint + `?query=${query}` + `&size=${size}` + `&page=${page}` + `&sort=${sortParam},${sortDirection}` );
  }

  // getCableById(id: string){
  //   let getCableByIdEndpoint = '/get-cable';
  //   this.httpService.get(this.baseURL + getCableByIdEndpoint, null, id).subscribe( response => {
  //     console.log("got the cable", response);
  //   })
  // }

  addCable(cable: Cable) {
    let saveEndpoint = '/save';
    return this.httpService.post( this.baseURL + saveEndpoint, cable);
  }

  deleteCable(id: number, size: number, page: number, sortParam: string, sortDirection: string){
    return this.httpService.delete(this.baseURL + `?cableId=${id}` + `&size=${size}` + `&page=${page}` + `&sort=${sortParam},${sortDirection}`);
  }
}

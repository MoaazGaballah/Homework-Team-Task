import {Injectable} from "@angular/core";
import {HttpService} from "./common/http.service";
import {Observable} from "rxjs";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class DataService {

  private baseURL = 'http://localhost:10010/data';

  constructor(
    private httpService: HttpService
  ) {
  }

  getAllModelClasses(): Observable<any> {
    let getAllModelClassesEndpoint = '/model-classes';
    return this.httpService.get(
      this.baseURL + getAllModelClassesEndpoint
    );
  }

  getPreviewData(exportDataModel: {}): Observable<any> {
    let exportEndpoint = '/export';
    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.httpService.postWithHeadersAndResponseTypes(
      this.baseURL + exportEndpoint,
      exportDataModel,
      headers,
      'text'
    )
  }
}

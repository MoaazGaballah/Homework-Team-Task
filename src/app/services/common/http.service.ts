import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({ providedIn: 'root' })
export class HttpService {

  constructor(
    private http: HttpClient
  ) {
  }

  get(url: string) {
    return this.http.get(url)
  }

  post(url: string, body: Object) {
    return this.http.post(url, body);
  }

  postWithHeadersAndResponseTypes(url: string, body: Object, headers: HttpHeaders, responseType : string){
    switch (responseType) {
      case 'text':
        return this.http.post(url, body, {headers, responseType: 'text'});
    }
    // other types of http post requests
    return this.http.post(url, body, {headers, responseType:'json'});
  }

  delete(url: string) {
    return this.http.delete(url);
  }
}

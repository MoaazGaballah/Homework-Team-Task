import {HttpClient, HttpParams} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable({ providedIn: 'root' })
export class HttpService {

  constructor(
    private http: HttpClient
  ) {
  }

  get(url: string) {
    //
    // let params = new HttpParams();
    //
    // if (pageNo != null)
    //   params.set('page', pageNo);
    // if (id != null)
    //   params.set('sort=id', id);

    return this.http.get(url)
  }

  post(url: string, body: Object) {
    return this.http.post(url, body);
  }

  delete(url: string) {
    return this.http.delete(url);
  }
}

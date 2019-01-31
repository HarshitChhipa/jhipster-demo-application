import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAuth_logs } from 'app/shared/model/auth-logs.model';

type EntityResponseType = HttpResponse<IAuth_logs>;
type EntityArrayResponseType = HttpResponse<IAuth_logs[]>;

@Injectable({ providedIn: 'root' })
export class Auth_logsService {
    public resourceUrl = SERVER_API_URL + 'api/auth-logs';

    constructor(protected http: HttpClient) {}

    create(auth_logs: IAuth_logs): Observable<EntityResponseType> {
        return this.http.post<IAuth_logs>(this.resourceUrl, auth_logs, { observe: 'response' });
    }

    update(auth_logs: IAuth_logs): Observable<EntityResponseType> {
        return this.http.put<IAuth_logs>(this.resourceUrl, auth_logs, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IAuth_logs>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAuth_logs[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

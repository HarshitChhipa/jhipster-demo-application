import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILeads } from 'app/shared/model/leads.model';

type EntityResponseType = HttpResponse<ILeads>;
type EntityArrayResponseType = HttpResponse<ILeads[]>;

@Injectable({ providedIn: 'root' })
export class LeadsService {
    public resourceUrl = SERVER_API_URL + 'api/leads';

    constructor(protected http: HttpClient) {}

    create(leads: ILeads): Observable<EntityResponseType> {
        return this.http.post<ILeads>(this.resourceUrl, leads, { observe: 'response' });
    }

    update(leads: ILeads): Observable<EntityResponseType> {
        return this.http.put<ILeads>(this.resourceUrl, leads, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ILeads>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILeads[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

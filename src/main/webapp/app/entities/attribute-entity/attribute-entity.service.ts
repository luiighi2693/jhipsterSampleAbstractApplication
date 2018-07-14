import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';

type EntityResponseType = HttpResponse<IAttributeEntity>;
type EntityArrayResponseType = HttpResponse<IAttributeEntity[]>;

@Injectable({ providedIn: 'root' })
export class AttributeEntityService {
    private resourceUrl = SERVER_API_URL + 'api/attribute-entities';

    constructor(private http: HttpClient) {}

    create(attributeEntity: IAttributeEntity): Observable<EntityResponseType> {
        return this.http.post<IAttributeEntity>(this.resourceUrl, attributeEntity, { observe: 'response' });
    }

    update(attributeEntity: IAttributeEntity): Observable<EntityResponseType> {
        return this.http.put<IAttributeEntity>(this.resourceUrl, attributeEntity, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IAttributeEntity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAttributeEntity[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

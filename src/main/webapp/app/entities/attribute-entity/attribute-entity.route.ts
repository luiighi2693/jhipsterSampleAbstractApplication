import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AttributeEntity } from 'app/shared/model/attribute-entity.model';
import { AttributeEntityService } from './attribute-entity.service';
import { AttributeEntityComponent } from './attribute-entity.component';
import { AttributeEntityDetailComponent } from './attribute-entity-detail.component';
import { AttributeEntityUpdateComponent } from './attribute-entity-update.component';
import { AttributeEntityDeletePopupComponent } from './attribute-entity-delete-dialog.component';
import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';

@Injectable({ providedIn: 'root' })
export class AttributeEntityResolve implements Resolve<IAttributeEntity> {
    constructor(private service: AttributeEntityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((attributeEntity: HttpResponse<AttributeEntity>) => attributeEntity.body));
        }
        return of(new AttributeEntity());
    }
}

export const attributeEntityRoute: Routes = [
    {
        path: 'attribute-entity',
        component: AttributeEntityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttributeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute-entity/:id/view',
        component: AttributeEntityDetailComponent,
        resolve: {
            attributeEntity: AttributeEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttributeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute-entity/new',
        component: AttributeEntityUpdateComponent,
        resolve: {
            attributeEntity: AttributeEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttributeEntities'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attribute-entity/:id/edit',
        component: AttributeEntityUpdateComponent,
        resolve: {
            attributeEntity: AttributeEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttributeEntities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attributeEntityPopupRoute: Routes = [
    {
        path: 'attribute-entity/:id/delete',
        component: AttributeEntityDeletePopupComponent,
        resolve: {
            attributeEntity: AttributeEntityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttributeEntities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

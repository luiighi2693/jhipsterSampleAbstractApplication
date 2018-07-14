import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    AttributeEntityComponent,
    AttributeEntityDetailComponent,
    AttributeEntityUpdateComponent,
    AttributeEntityDeletePopupComponent,
    AttributeEntityDeleteDialogComponent,
    attributeEntityRoute,
    attributeEntityPopupRoute
} from './';

const ENTITY_STATES = [...attributeEntityRoute, ...attributeEntityPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttributeEntityComponent,
        AttributeEntityDetailComponent,
        AttributeEntityUpdateComponent,
        AttributeEntityDeleteDialogComponent,
        AttributeEntityDeletePopupComponent
    ],
    entryComponents: [
        AttributeEntityComponent,
        AttributeEntityUpdateComponent,
        AttributeEntityDeleteDialogComponent,
        AttributeEntityDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAttributeEntityModule {}

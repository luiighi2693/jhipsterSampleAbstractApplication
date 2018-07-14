import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationMetadataModule } from './metadata/metadata.module';
import { JhipsterSampleApplicationAttributeEntityModule } from './attribute-entity/attribute-entity.module';
import { JhipsterSampleApplicationPersonModule } from './person/person.module';
import { JhipsterSampleApplicationPartyModule } from './party/party.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplicationMetadataModule,
        JhipsterSampleApplicationAttributeEntityModule,
        JhipsterSampleApplicationPersonModule,
        JhipsterSampleApplicationPartyModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}

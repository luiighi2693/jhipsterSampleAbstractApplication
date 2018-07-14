import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';

@Component({
    selector: 'jhi-attribute-entity-detail',
    templateUrl: './attribute-entity-detail.component.html'
})
export class AttributeEntityDetailComponent implements OnInit {
    attributeEntity: IAttributeEntity;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attributeEntity }) => {
            this.attributeEntity = attributeEntity;
        });
    }

    previousState() {
        window.history.back();
    }
}

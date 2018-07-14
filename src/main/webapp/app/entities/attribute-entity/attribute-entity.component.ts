import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';
import { Principal } from 'app/core';
import { AttributeEntityService } from './attribute-entity.service';

@Component({
    selector: 'jhi-attribute-entity',
    templateUrl: './attribute-entity.component.html'
})
export class AttributeEntityComponent implements OnInit, OnDestroy {
    attributeEntities: IAttributeEntity[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private attributeEntityService: AttributeEntityService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.attributeEntityService.query().subscribe(
            (res: HttpResponse<IAttributeEntity[]>) => {
                this.attributeEntities = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAttributeEntities();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAttributeEntity) {
        return item.id;
    }

    registerChangeInAttributeEntities() {
        this.eventSubscriber = this.eventManager.subscribe('attributeEntityListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

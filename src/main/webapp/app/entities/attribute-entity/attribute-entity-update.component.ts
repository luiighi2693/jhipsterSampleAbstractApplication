import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';
import { AttributeEntityService } from './attribute-entity.service';

@Component({
    selector: 'jhi-attribute-entity-update',
    templateUrl: './attribute-entity-update.component.html'
})
export class AttributeEntityUpdateComponent implements OnInit {
    private _attributeEntity: IAttributeEntity;
    isSaving: boolean;

    constructor(private attributeEntityService: AttributeEntityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attributeEntity }) => {
            this.attributeEntity = attributeEntity;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.attributeEntity.id !== undefined) {
            this.subscribeToSaveResponse(this.attributeEntityService.update(this.attributeEntity));
        } else {
            this.subscribeToSaveResponse(this.attributeEntityService.create(this.attributeEntity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAttributeEntity>>) {
        result.subscribe((res: HttpResponse<IAttributeEntity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get attributeEntity() {
        return this._attributeEntity;
    }

    set attributeEntity(attributeEntity: IAttributeEntity) {
        this._attributeEntity = attributeEntity;
    }
}

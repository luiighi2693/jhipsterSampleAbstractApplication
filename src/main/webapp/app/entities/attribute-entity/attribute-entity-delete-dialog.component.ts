import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttributeEntity } from 'app/shared/model/attribute-entity.model';
import { AttributeEntityService } from './attribute-entity.service';

@Component({
    selector: 'jhi-attribute-entity-delete-dialog',
    templateUrl: './attribute-entity-delete-dialog.component.html'
})
export class AttributeEntityDeleteDialogComponent {
    attributeEntity: IAttributeEntity;

    constructor(
        private attributeEntityService: AttributeEntityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.attributeEntityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attributeEntityListModification',
                content: 'Deleted an attributeEntity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attribute-entity-delete-popup',
    template: ''
})
export class AttributeEntityDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attributeEntity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttributeEntityDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.attributeEntity = attributeEntity;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

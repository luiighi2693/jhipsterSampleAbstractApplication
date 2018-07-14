/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AttributeEntityUpdateComponent } from 'app/entities/attribute-entity/attribute-entity-update.component';
import { AttributeEntityService } from 'app/entities/attribute-entity/attribute-entity.service';
import { AttributeEntity } from 'app/shared/model/attribute-entity.model';

describe('Component Tests', () => {
    describe('AttributeEntity Management Update Component', () => {
        let comp: AttributeEntityUpdateComponent;
        let fixture: ComponentFixture<AttributeEntityUpdateComponent>;
        let service: AttributeEntityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AttributeEntityUpdateComponent]
            })
                .overrideTemplate(AttributeEntityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttributeEntityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttributeEntityService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttributeEntity('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attributeEntity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttributeEntity();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attributeEntity = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});

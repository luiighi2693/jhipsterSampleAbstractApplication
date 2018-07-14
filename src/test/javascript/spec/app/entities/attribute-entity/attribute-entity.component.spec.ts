/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AttributeEntityComponent } from 'app/entities/attribute-entity/attribute-entity.component';
import { AttributeEntityService } from 'app/entities/attribute-entity/attribute-entity.service';
import { AttributeEntity } from 'app/shared/model/attribute-entity.model';

describe('Component Tests', () => {
    describe('AttributeEntity Management Component', () => {
        let comp: AttributeEntityComponent;
        let fixture: ComponentFixture<AttributeEntityComponent>;
        let service: AttributeEntityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AttributeEntityComponent],
                providers: []
            })
                .overrideTemplate(AttributeEntityComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttributeEntityComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttributeEntityService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AttributeEntity('123')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.attributeEntities[0]).toEqual(jasmine.objectContaining({ id: '123' }));
        });
    });
});

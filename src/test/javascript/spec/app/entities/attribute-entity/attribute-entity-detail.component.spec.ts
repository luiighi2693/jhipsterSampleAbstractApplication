/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { AttributeEntityDetailComponent } from 'app/entities/attribute-entity/attribute-entity-detail.component';
import { AttributeEntity } from 'app/shared/model/attribute-entity.model';

describe('Component Tests', () => {
    describe('AttributeEntity Management Detail Component', () => {
        let comp: AttributeEntityDetailComponent;
        let fixture: ComponentFixture<AttributeEntityDetailComponent>;
        const route = ({ data: of({ attributeEntity: new AttributeEntity('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [AttributeEntityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttributeEntityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttributeEntityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attributeEntity).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});

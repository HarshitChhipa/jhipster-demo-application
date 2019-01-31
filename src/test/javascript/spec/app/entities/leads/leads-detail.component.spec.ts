/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyAppTestModule } from '../../../test.module';
import { LeadsDetailComponent } from 'app/entities/leads/leads-detail.component';
import { Leads } from 'app/shared/model/leads.model';

describe('Component Tests', () => {
    describe('Leads Management Detail Component', () => {
        let comp: LeadsDetailComponent;
        let fixture: ComponentFixture<LeadsDetailComponent>;
        const route = ({ data: of({ leads: new Leads('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyAppTestModule],
                declarations: [LeadsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LeadsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LeadsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.leads).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});

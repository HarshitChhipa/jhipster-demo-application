/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyAppTestModule } from '../../../test.module';
import { LeadsUpdateComponent } from 'app/entities/leads/leads-update.component';
import { LeadsService } from 'app/entities/leads/leads.service';
import { Leads } from 'app/shared/model/leads.model';

describe('Component Tests', () => {
    describe('Leads Management Update Component', () => {
        let comp: LeadsUpdateComponent;
        let fixture: ComponentFixture<LeadsUpdateComponent>;
        let service: LeadsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyAppTestModule],
                declarations: [LeadsUpdateComponent]
            })
                .overrideTemplate(LeadsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LeadsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LeadsService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Leads('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.leads = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Leads();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.leads = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});

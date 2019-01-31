/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { MyAppTestModule } from '../../../test.module';
import { Auth_logsUpdateComponent } from 'app/entities/auth-logs/auth-logs-update.component';
import { Auth_logsService } from 'app/entities/auth-logs/auth-logs.service';
import { Auth_logs } from 'app/shared/model/auth-logs.model';

describe('Component Tests', () => {
    describe('Auth_logs Management Update Component', () => {
        let comp: Auth_logsUpdateComponent;
        let fixture: ComponentFixture<Auth_logsUpdateComponent>;
        let service: Auth_logsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyAppTestModule],
                declarations: [Auth_logsUpdateComponent]
            })
                .overrideTemplate(Auth_logsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(Auth_logsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(Auth_logsService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Auth_logs('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.auth_logs = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Auth_logs();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.auth_logs = entity;
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

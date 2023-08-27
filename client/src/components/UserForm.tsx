import {
  IonAlert,
  IonButton,
  IonInput,
  IonItem,
  useIonAlert,
} from "@ionic/react";
import { FormEvent, useCallback, useEffect } from "react";

export function UserForm() {
  const [presentAlert] = useIonAlert();
  const onSubmit = useCallback(async (event: FormEvent) => {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const lenOfFirstName = form.first_name.value.trim().length;
    const lenOfLastName = form.last_name.value.trim().length;
    if (lenOfFirstName == 0 && lenOfLastName == 0) {
      presentAlert({ header: "Please fill in first name and last name!" });
      return;
    } else if (lenOfFirstName == 0) {
      presentAlert({ header: "Please fill in first name!" });
      return;
    } else if (lenOfLastName == 0) {
      presentAlert({ header: "Please fill in last name!" });
      return;
    }

    try {
      let res = await fetch("http://localhost:8080/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },

        body: JSON.stringify({
          first_name: form.first_name.value,
          last_name: form.last_name.value,
        }),
      });
      let json = await res.json();
      console.log(json);
      if (typeof json == "object" && !Array.isArray(json) && "error" in json) {
        let errorMsg = JSON.stringify(json.error);
        presentAlert({ header: errorMsg });
      } else {
        presentAlert({ header: "Submission Successful" });
      }
    } catch (error) {
      console.log(error);
    }
  }, []);
  return (
    <>
      <IonItem>Please input your full name below</IonItem>
      <form id="nameForm" action="/users" method="POST" onSubmit={onSubmit}>
        <IonItem>
          <IonInput
            label="First Name"
            name="first_name"
            placeholder="input here"
            type="text"
          ></IonInput>
        </IonItem>
        <IonItem>
          <IonInput
            label="Last Name"
            name="last_name"
            placeholder="input here"
            type="text"
          ></IonInput>
        </IonItem>
        <IonButton type="submit">Submit</IonButton>
      </form>
    </>
  );
}

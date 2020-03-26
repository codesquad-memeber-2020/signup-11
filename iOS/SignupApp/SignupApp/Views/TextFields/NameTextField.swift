//
//  NameTextField.swift
//  SignupApp
//
//  Created by kimdo2297 on 2020/03/25.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

final class NameTextField: CustomTextField {
    func setMessageCorrectName() {
        let messageCorrectName = "사용가능한 이름입니다."
        Util.setCorrectMessage(messageLabel, text: messageCorrectName)
    }
}
